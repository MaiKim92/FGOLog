package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.PartyBusiness;
import com.kimmai.fgolog.business.ServantBusiness;
import com.kimmai.fgolog.domain.PartyMember;
import com.kimmai.fgolog.service.PartyMemberService;
import com.kimmai.fgolog.service.PartyService;
import com.kimmai.fgolog.business.ServantBusiness;
import com.kimmai.fgolog.service.dto.PartyDTO;
import com.kimmai.fgolog.service.dto.PartyMemberDTO;
import com.kimmai.fgolog.web.rest.dto.PartyResponseDTO;
import com.kimmai.fgolog.service.dto.ServantDTO;
import java.util.List;
import java.util.LinkedList;
import java.util.Optional;

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


    public PartyBusinessImpl(PartyService partyService, PartyMemberService partyMemberService, ServantBusiness servantBusiness) {
        this.partyService = partyService;
        this.partyMemberService = partyMemberService;
        this.servantBusiness = servantBusiness;
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
                PartyResponseDTO resp = new PartyResponseDTO();
                resp.setName(party.getName());
                resp.setServants(PadParty(servantsInParty));
                result.add(resp);
            });
            return result;
        } catch (Exception e) {
            log.debug("Get all parties failed");
            throw e;
        }
    }

    private static List<ServantResponseDTO> PadParty(List<ServantResponseDTO> party) {
        if (party.size() > 6) {
            return party;
        }
        ServantDTO newServant = new ServantDTO();
        newServant.setName("No data");
        newServant.setImageUrl("view/assets/img/servant/No_Data.png");
        newServant.setThumbnailUrl("view/assets/img/servant/thumb/No_Data.png");
        ServantResponseDTO resp = new ServantResponseDTO();
        resp.setServant(newServant);
        while (party.size() < 6) {
            party.add(resp);
        }
        return party;
    }

}
