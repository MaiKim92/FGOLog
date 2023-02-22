package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.PartyBusiness;
import com.kimmai.fgolog.business.ServantBusiness;
import com.kimmai.fgolog.service.*;
import com.kimmai.fgolog.service.dto.*;
import com.kimmai.fgolog.web.rest.dto.PartyRequestDTO;
import com.kimmai.fgolog.web.rest.dto.PartyResponseDTO;

import java.util.*;

import com.kimmai.fgolog.web.rest.dto.ServantResponseDTO;
import com.kimmai.fgolog.web.rest.dto.ServantResponseDTO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PartyBusinessImpl implements PartyBusiness {

    private final Logger log = LoggerFactory.getLogger(PartyBusinessImpl.class);

    private final PartyService partyService;

    private final PartyMemberService partyMemberService;

    private final ServantBusiness servantBusiness;

    private final ServantService servantService;

    private final MysticCodeService mysticCodeService;

    private final CraftEssenceService craftEssenceService;

    private final SkillService skillService;

    public PartyBusinessImpl(PartyService partyService, PartyMemberService partyMemberService, ServantBusiness servantBusiness, ServantService servantService, MysticCodeService mysticCodeService, CraftEssenceService craftEssenceService, SkillService skillService) {
        this.partyService = partyService;
        this.partyMemberService = partyMemberService;
        this.servantBusiness = servantBusiness;
        this.servantService = servantService;
        this.mysticCodeService = mysticCodeService;
        this.craftEssenceService = craftEssenceService;
        this.skillService = skillService;
    }

    @Override
    public List<PartyResponseDTO> getAllParties() {
        try {
            List<PartyResponseDTO> result = new LinkedList<>();
            List<PartyDTO> parties = partyService.findAll();
            parties.forEach(party -> {
                List<PartyMemberResponseDTO> servantsInParty = new LinkedList<>();
                List<PartyMemberDTO> partyMembers = partyMemberService.findAllByPartyId(party.getId());
                partyMembers.forEach(member -> {
                    ServantResponseDTO servant = servantBusiness.findOne(member.getServant().getId());
                    List<SkillDTO> skills = skillService.findAllByServantId(member.getServant().getId());
                    PartyMemberResponseDTO partyMember = new PartyMemberResponseDTO();
                    partyMember.setServant(servant.getServant());
                    partyMember.setSkills(skills);
                    CraftEssenceDTO craftEssence = null;
                    if (member.getCraftEssence() != null) {
                        craftEssence = craftEssenceService.findOne(member.getCraftEssence().getId()).orElseThrow();
                    }
                    partyMember.setCraftEssence(craftEssence);
                    servantsInParty.add(partyMember);
                });
                MysticCodeDTO mysticCode = mysticCodeService.findOne(party.getMysticCode().getId()).orElseThrow();
                PartyResponseDTO resp = new PartyResponseDTO();
                resp.setId(party.getId());
                resp.setName(party.getName());
                resp.setServants(servantsInParty);
                resp.setMysticCode(mysticCode);
                result.add(resp);
            });
            return result;
        } catch (Exception e) {
            log.debug("Get all parties failed");
            throw e;
        }
    }

    @Override
    public PartyResponseDTO create(PartyRequestDTO req) {
        PartyDTO party = new PartyDTO();
        party.setName(req.getName());
        MysticCodeDTO mysticCodeDTO = mysticCodeService.findOne(req.getMysticCodeId()).orElseThrow();
        party.setMysticCode(mysticCodeDTO);
        List<PartyRequestDTO.PartyMemberRequestDTO> members = req.getPartyMembers();
        List<PartyMemberDTO> partyMembers = new ArrayList<>();
        for(int i = 0; i < members.size(); i++) {
            ServantDTO servant = servantService.findOne(members.get(i).getServantId()).orElseThrow();
            CraftEssenceDTO ce = craftEssenceService.findOne(members.get(i).getCraftEssenceId()).orElse(null);
            PartyMemberDTO member = new PartyMemberDTO();
            member.setServant(servant);
            member.setCraftEssence(ce);
            member.setSeq(i + 1);
            partyMembers.add(member);
        }
        PartyDTO saved = partyService.save(party);
        partyMembers.forEach(member -> {
            member.setParty(saved);
            partyMemberService.save(member);
        });
        PartyResponseDTO resp = new PartyResponseDTO();
        resp.setId(saved.getId());
        resp.setName(saved.getName());
        resp.setMysticCode(saved.getMysticCode());
        return resp;
    }

    @Override
    public PartyResponseDTO update(Long id, PartyRequestDTO req) {
        PartyDTO party = new PartyDTO();
        party.setId(id);
        party.setName(req.getName());
        MysticCodeDTO mysticCodeDTO = mysticCodeService.findOne(req.getMysticCodeId()).orElseThrow();
        party.setMysticCode(mysticCodeDTO);
        List<Long> existingPartyMemberIds = partyMemberService.findAllByPartyId(id).stream().map(PartyMemberDTO::getId).collect(Collectors.toList());
        existingPartyMemberIds.forEach(partyMemberService::delete);
        List<PartyRequestDTO.PartyMemberRequestDTO> members = req.getPartyMembers();
        List<PartyMemberDTO> partyMembers = new ArrayList<>();
        for(int i = 0; i < members.size(); i++) {
            ServantDTO servant = servantService.findOne(members.get(i).getServantId()).orElseThrow();
            CraftEssenceDTO ce = craftEssenceService.findOne(members.get(i).getCraftEssenceId()).orElse(null);
            PartyMemberDTO member = new PartyMemberDTO();
            member.setServant(servant);
            member.setCraftEssence(ce);
            member.setSeq(i + 1);
            partyMembers.add(member);
        }
        PartyDTO saved = partyService.save(party);
        partyMembers.forEach(member -> {
            member.setParty(saved);
            partyMemberService.save(member);
        });
        PartyResponseDTO resp = new PartyResponseDTO();
        resp.setId(saved.getId());
        resp.setName(saved.getName());
        resp.setMysticCode(saved.getMysticCode());
        return resp;
    }

    @Override
    public Optional<PartyResponseDTO> findOne(Long id) {
        PartyDTO party = partyService.findOne(id).orElse(null);
        if (party == null) {
            return Optional.empty();
        }
        PartyResponseDTO response = new PartyResponseDTO();
        response.setId(party.getId());
        response.setName(party.getName());
        response.setMysticCode(party.getMysticCode());
        List<PartyMemberResponseDTO> members = partyMemberService.findAllByPartyId(party.getId()).stream().map(pm -> {
            PartyMemberResponseDTO partyMemberResponseDTO = new PartyMemberResponseDTO();
            CraftEssenceDTO craftEssenceDTO = null;
            if (pm.getCraftEssence() != null) {
                craftEssenceDTO = craftEssenceService.findOne(pm.getCraftEssence().getId()).orElse(null);
            }
            partyMemberResponseDTO.setCraftEssence(craftEssenceDTO);
            ServantResponseDTO servant = servantBusiness.findOne(pm.getServant().getId());
            partyMemberResponseDTO.setSkills(servant.getSkills());
            partyMemberResponseDTO.setServant(servant.getServant());
            partyMemberResponseDTO.setCommandCards(servant.getCommandCards());
            return partyMemberResponseDTO;
        }).collect(Collectors.toList());
        partyMemberService.findAllByPartyId(party.getId());
        response.setServants(members);
        return Optional.of(response);
    }

}
