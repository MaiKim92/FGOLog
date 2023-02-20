package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.CommandCardBusiness;
import com.kimmai.fgolog.business.ServantBusiness;
import com.kimmai.fgolog.domain.enumeration.CardType;
import com.kimmai.fgolog.service.CommandCardService;
import com.kimmai.fgolog.service.PartyMemberService;
import com.kimmai.fgolog.service.ServantService;
import com.kimmai.fgolog.service.SkillService;
import com.kimmai.fgolog.service.dto.CommandCardDTO;
import com.kimmai.fgolog.service.dto.PartyMemberDTO;
import com.kimmai.fgolog.service.dto.ServantDTO;
import com.kimmai.fgolog.service.dto.SkillDTO;
import com.kimmai.fgolog.web.rest.dto.ServantRequestDTO;
import com.kimmai.fgolog.web.rest.dto.ServantResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServantBusinessImpl implements ServantBusiness {

    private final Logger log = LoggerFactory.getLogger(ServantBusinessImpl.class);

    private final ServantService servantService;

    private final CommandCardBusiness commandCardBusiness;

    private final SkillService skillService;

    private final CommandCardService commandCardService;

    private final PartyMemberService partyMemberService;

    public ServantBusinessImpl(ServantService servantService, CommandCardBusiness commandCardBusiness, SkillService skillService, CommandCardService commandCardService, PartyMemberService partyMemberService) {
        this.servantService = servantService;
        this.commandCardBusiness = commandCardBusiness;
        this.skillService = skillService;
        this.commandCardService = commandCardService;
        this.partyMemberService = partyMemberService;
    }
    @Override
    public List<ServantResponseDTO> findAll() {
        List<ServantResponseDTO> response = new ArrayList<>();
        List<ServantDTO> servants = servantService.findAll();
        servants.forEach(servant -> {
            List<CommandCardDTO> commandCards = commandCardBusiness.getByServantId(servant.getId());
            List<SkillDTO> skills = skillService.getByServantId(servant.getId());
            ServantResponseDTO resp = new ServantResponseDTO();
            resp.setServant(servant);
            resp.setCommandCards(commandCards);
            resp.setSkills(skills);
            response.add(resp);
        });
        return response;
    }
    @Override
    public List<ServantDTO> findAllInParty(Long partyId) {
        List<Long> includedIds = partyMemberService.findAllServantIdsByPartyId(partyId);
        return servantService.findAll().stream().filter(servant -> includedIds.contains(servant.getId())).collect(Collectors.toList());
    }
    @Override
    public List<ServantDTO> findAllExcludeParty(Long partyId) {
        List<Long> excludedIds = partyMemberService.findAllServantIdsByPartyId(partyId);
        return servantService.findAll().stream().filter(servant -> !excludedIds.contains(servant.getId())).collect(Collectors.toList());
    }
    @Override
    public List<ServantResponseDTO> getAllOwnedServants() {
        List<ServantResponseDTO> response = new ArrayList<>();
        List<ServantDTO> servants = servantService.findAllOwned();
        servants.forEach(servant -> {
            List<CommandCardDTO> commandCards = commandCardBusiness.getByServantId(servant.getId());
            List<SkillDTO> skills = skillService.getByServantId(servant.getId());
            ServantResponseDTO resp = new ServantResponseDTO();
            resp.setServant(servant);
            resp.setCommandCards(commandCards);
            resp.setSkills(skills);
            response.add(resp);
        });
        return response;
    }

    @Override
    public ServantResponseDTO findOne(Long id) {
        ServantDTO servant = servantService.findOne(id).orElseThrow();
        ServantResponseDTO resp = new ServantResponseDTO();
        resp.setServant(servant);
        resp.setCommandCards(commandCardBusiness.getByServantId(servant.getId()));
        resp.setSkills(skillService.getByServantId(servant.getId()));
        return resp;
    }

    @Override
    @Transactional
    public ServantResponseDTO create(ServantRequestDTO req) {
        List<String> commandCards = req.getCommandCards();
        ServantDTO servant = new ServantDTO();
        servant.setName(req.getName());
        servant.setLevel(req.getLevel());
        servant.setImageUrl(req.getImageUrl());
        servant.setThumbnailUrl(req.getThumbnailUrl());
        servant.setNpLevel(req.getNpLevel());
        ServantDTO saved = servantService.save(servant);
        List<SkillDTO> skills = skillService.findById(req.getSkills());
        List<CommandCardDTO> cards = new ArrayList<>();
        for (int i = 0; i < commandCards.size(); i++) {
            CommandCardDTO cardDTO = new CommandCardDTO();
            cardDTO.setServant(saved);
            cardDTO.setSeq(i);
            cardDTO.setType(getCardType(commandCards.get(i)));
            cards.add(commandCardService.save(cardDTO));
        }
        for (int i = 0; i < skills.size(); i++) {
            skills.get(i).setServant(saved);
            skills.get(i).setSeq(i + 1);
            skillService.partialUpdate(skills.get(i));
        }
        ServantResponseDTO resp = new ServantResponseDTO();
        resp.setServant(saved);
        resp.setSkills(skills);
        resp.setCommandCards(cards);
        return resp;
    }

    @Override
    @Transactional
    public ServantResponseDTO update(Long id, ServantRequestDTO req) throws Exception {
        List<String> commandCards = req.getCommandCards();
        List<Long> skillIds = req.getSkills();
        if (skillIds.size() > 3) {
            throw new RuntimeException();
        }
        ServantDTO servant = new ServantDTO();
        servant.setId(id);
        servant.setName(req.getName());
        servant.setLevel(req.getLevel());
        servant.setImageUrl(req.getImageUrl());
        servant.setThumbnailUrl(req.getThumbnailUrl());
        servant.setNpLevel(req.getNpLevel());
        ServantDTO saved = servantService.partialUpdate(servant).orElseThrow();
        List<CommandCardDTO> cards = commandCardService.getAllByServantId(id);
        List<SkillDTO> existingSkills = skillService.getByServantId(saved.getId());
        existingSkills.forEach(skill -> {
                skill.setServant(null);
                skillService.partialUpdate(skill);
            });
        List<SkillDTO> updatedSkills = new ArrayList<>();
        for (int i = 0; i < skillIds.size(); i++) {
            SkillDTO skill = skillService.findOne(skillIds.get(i)).orElseThrow();
            skill.setServant(saved);
            skill.setSeq(i + 1);
            updatedSkills.add(skillService.partialUpdate(skill).orElseThrow());
        }
        for (int i = 0; i < commandCards.size(); i++) {
            CommandCardDTO cardDTO = new CommandCardDTO();
            cardDTO.setId(cards.get(i).getId());
            cardDTO.setServant(saved);
            cardDTO.setSeq(i);
            cardDTO.setType(getCardType(commandCards.get(i)));
            cards.set(i, commandCardService.partialUpdate(cardDTO).orElse(null));
        }
        ServantResponseDTO resp = new ServantResponseDTO();
        resp.setServant(saved);
        resp.setCommandCards(cards);
        resp.setSkills(updatedSkills);
        return resp;
    }

    private CardType getCardType(String card) {
        if (card.equals("Q")) {
            return CardType.QUICK;
        }
        if (card.equals("A")) {
            return CardType.ARTS;
        }
        return CardType.BUSTER;
    }
}
