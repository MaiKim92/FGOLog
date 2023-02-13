package com.kimmai.fgolog.web.rest.dto;

import com.kimmai.fgolog.service.dto.ServantDTO;
import com.kimmai.fgolog.service.dto.CommandCardDTO;
import com.kimmai.fgolog.service.dto.SkillDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ServantResponseDTO {

    private ServantDTO servant;

    private List<CommandCardDTO> commandCards;

    private List<SkillDTO> skills;

    public void setServant(ServantDTO servant) {
        this.servant = servant;
    }

    public void setCommandCards(List<CommandCardDTO> commandCards) {
        this.commandCards = commandCards;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public ServantDTO getServant() {
        return this.servant;
    }

    public List<CommandCardDTO> getCommandCards() {
        return this.commandCards;
    }

    public List<SkillDTO> getSkills() {
        return this.skills;
    }

}
