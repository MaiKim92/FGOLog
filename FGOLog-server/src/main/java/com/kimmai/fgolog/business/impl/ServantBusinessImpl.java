package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.CommandCardBusiness;
import com.kimmai.fgolog.business.ServantBusiness;
import com.kimmai.fgolog.service.CommandCardService;
import com.kimmai.fgolog.service.ServantService;
import com.kimmai.fgolog.service.WishItemService;
import com.kimmai.fgolog.service.dto.CommandCardDTO;
import com.kimmai.fgolog.service.dto.ServantDTO;
import com.kimmai.fgolog.web.rest.dto.ServantResponseDTO;
import com.kimmai.fgolog.service.mapper.ServantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServantBusinessImpl implements ServantBusiness {

    private final Logger log = LoggerFactory.getLogger(ServantBusinessImpl.class);

    private final ServantService servantService;

    private final CommandCardBusiness commandCardBusiness;

    public ServantBusinessImpl(ServantService servantService, CommandCardBusiness commandCardBusiness) {
        this.servantService = servantService;
        this.commandCardBusiness = commandCardBusiness;
    }
    @Override
    public List<ServantResponseDTO> getAllOwnedServants() {
        List<ServantResponseDTO> response = new ArrayList<>();
        List<ServantDTO> servants = servantService.findAllOwned();
        servants.forEach(servant -> {
            List<CommandCardDTO> commandCards = commandCardBusiness.getByServantId(servant.getId());
            ServantResponseDTO resp = new ServantResponseDTO();
            resp.setServant(servant);
            resp.setCommandCards(commandCards);
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
        return resp;
    }
}
