package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.PartyBusiness;
import com.kimmai.fgolog.business.ServantBusiness;
import com.kimmai.fgolog.service.MysticCodeService;
import com.kimmai.fgolog.service.PartyMemberService;
import com.kimmai.fgolog.service.PartyService;
import com.kimmai.fgolog.service.ServantService;
import com.kimmai.fgolog.service.dto.MysticCodeDTO;
import com.kimmai.fgolog.service.dto.PartyDTO;
import com.kimmai.fgolog.service.dto.PartyMemberDTO;
import com.kimmai.fgolog.web.rest.dto.PartyRequestDTO;
import com.kimmai.fgolog.web.rest.dto.PartyResponseDTO;
import com.kimmai.fgolog.service.dto.ServantDTO;

import java.util.*;

import com.kimmai.fgolog.web.rest.dto.ServantResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyBusinessImpl implements PartyBusiness {

    private final Logger log = LoggerFactory.getLogger(PartyBusinessImpl.class);

    private final PartyService partyService;

    private final PartyMemberService partyMemberService;

    private final ServantBusiness servantBusiness;

    private final ServantService servantService;

    private final MysticCodeService mysticCodeService;

    public PartyBusinessImpl(PartyService partyService, PartyMemberService partyMemberService, ServantBusiness servantBusiness, ServantService servantService, MysticCodeService mysticCodeService) {
        this.partyService = partyService;
        this.partyMemberService = partyMemberService;
        this.servantBusiness = servantBusiness;
        this.servantService = servantService;
        this.mysticCodeService = mysticCodeService;
    }

    @Override
    public List<PartyResponseDTO> getAllParties() {
        try {
            List<PartyResponseDTO> result = new LinkedList<>();
            List<PartyDTO> parties = partyService.findAll();
            parties.forEach(party -> {
                List<ServantResponseDTO> servantsInParty = new LinkedList<>();
                List<Long> ids = partyMemberService.findAllServantIdsByPartyId(party.getId());
                ids.forEach(id -> {
                    ServantResponseDTO servant = servantBusiness.findOne(id);
                    servantsInParty.add(servant);
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
        List<Long> servantIds = req.getServantIds();
        List<PartyMemberDTO> partyMembers = new ArrayList<>();
        for(int i = 0; i < servantIds.size(); i++) {
            ServantDTO servant = servantService.findOne(servantIds.get(i)).orElseThrow();
            PartyMemberDTO member = new PartyMemberDTO();
            member.setServant(servant);
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
        return resp;
    }

    @Override
    public PartyResponseDTO update(Long id, PartyRequestDTO req) {
        PartyDTO party = new PartyDTO();
        party.setId(id);
        party.setName(req.getName());
        List<Long> servantIds = req.getServantIds();
        List<PartyMemberDTO> partyMembers = new ArrayList<>();
        List<Long> existingPartyMemberIds = partyMemberService.findAll().stream().filter(partyMember -> partyMember.getParty().getId() == id).map(PartyMemberDTO::getId).collect(Collectors.toList());
        existingPartyMemberIds.forEach(pm -> partyMemberService.delete(pm));
        for(int i = 0; i < servantIds.size(); i++) {
            ServantDTO servant = servantService.findOne(servantIds.get(i)).orElseThrow();
            PartyMemberDTO member = new PartyMemberDTO();
            member.setServant(servant);
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
        return resp;
    }

}
