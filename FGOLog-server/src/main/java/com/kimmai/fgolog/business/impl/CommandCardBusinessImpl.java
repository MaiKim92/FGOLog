package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.CommandCardBusiness;
import com.kimmai.fgolog.service.CommandCardService;
import com.kimmai.fgolog.service.CommandCodeService;
import com.kimmai.fgolog.service.dto.CommandCardDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommandCardBusinessImpl implements CommandCardBusiness {

    private CommandCardService commandCardService;

    private CommandCodeService commandCodeService;

    public CommandCardBusinessImpl(CommandCardService commandCardService, CommandCodeService commandCodeService) {
        this.commandCardService = commandCardService;
        this.commandCodeService = commandCodeService;
    }

    @Override
    public List<CommandCardDTO> getByServantId(Long servantId) {
        List<CommandCardDTO> commandCards = commandCardService.getAllByServantId(servantId);
        commandCards.forEach(commandCard ->
            commandCard.setCommandCode(commandCard.getCommandCode() == null ? null : commandCodeService.findOne(commandCard.getCommandCode().getId()).orElse(null)));
        return commandCards;
    }
}
