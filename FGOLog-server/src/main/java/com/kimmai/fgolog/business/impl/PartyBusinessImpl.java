package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.PartyBusiness;
import com.kimmai.fgolog.domain.PartyMember;
import com.kimmai.fgolog.service.PartyMemberService;
import com.kimmai.fgolog.service.PartyService;
import com.kimmai.fgolog.service.ServantService;
import com.kimmai.fgolog.service.dto.PartyDTO;
import com.kimmai.fgolog.service.dto.PartyMemberDTO;
import com.kimmai.fgolog.service.dto.PartyResponseDTO;
import com.kimmai.fgolog.service.dto.ServantDTO;
import java.util.List;
import java.util.LinkedList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class PartyBusinessImpl implements PartyBusiness {

    private final Logger log = LoggerFactory.getLogger(PartyBusinessImpl.class);

    private final PartyService partyService;

    private final PartyMemberService partyMemberService;

    private final ServantService servantService;


    public PartyBusinessImpl(PartyService partyService, PartyMemberService partyMemberService, ServantService servantService) {
        this.partyService = partyService;
        this.partyMemberService = partyMemberService;
        this.servantService = servantService;
    }

    @Override
    public List<PartyResponseDTO> getAllParties() {
        try {
            List<PartyResponseDTO> result = new LinkedList<>();
            List<PartyDTO> parties = partyService.findAll();
            parties.forEach(party -> {
                List<ServantDTO> servantsInParty = new LinkedList<>();
                List<Long> ids = partyMemberService.findAllServantIdsByPartyId(party.getId());
                ids.forEach(id -> {
                    ServantDTO servant = servantService.findOne(id).orElseThrow();
                    servantsInParty.add(servant);
                });
                PartyResponseDTO resp = new PartyResponseDTO();
                resp.setName(party.getName());
                resp.setServants(servantsInParty);
                result.add(resp);
            });
            return result;
        } catch (Exception e) {
            log.debug("Get all parties failed");
            throw e;
        }
    }

}
