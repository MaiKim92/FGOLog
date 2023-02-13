package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.CommandCardBusiness;
import com.kimmai.fgolog.business.ServantBusiness;
import com.kimmai.fgolog.service.ServantService;
import com.kimmai.fgolog.service.SkillService;
import com.kimmai.fgolog.service.dto.CommandCardDTO;
import com.kimmai.fgolog.service.dto.ServantDTO;
import com.kimmai.fgolog.service.dto.SkillDTO;
import com.kimmai.fgolog.web.rest.dto.ServantResponseDTO;
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

    private final SkillService skillService;

    public ServantBusinessImpl(ServantService servantService, CommandCardBusiness commandCardBusiness, SkillService skillService) {
        this.servantService = servantService;
        this.commandCardBusiness = commandCardBusiness;
        this.skillService = skillService;
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
}
