package com.kimmai.fgolog.web.rest.dto;

import com.kimmai.fgolog.service.dto.ServantDTO;
import com.kimmai.fgolog.service.dto.CommandCardDTO;

import java.util.List;

public class ServantResponseDTO {

    private ServantDTO servant;

    private List<CommandCardDTO> commandCards;

    public void setServant(ServantDTO servant) {
        this.servant = servant;
    }

    public void setCommandCards(List<CommandCardDTO> commandCards) {
        this.commandCards = commandCards;
    }

    public ServantDTO getServant() {
        return this.servant;
    }

    public List<CommandCardDTO> getCommandCards() {
        return this.commandCards;
    }

}
