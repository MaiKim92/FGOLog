package com.kimmai.fgolog.web.rest.dto;

import com.kimmai.fgolog.domain.enumeration.ServantClass;
import com.kimmai.fgolog.service.dto.CraftEssenceDTO;
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

    private ServantClass servantClass;

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

    public void setServantClass(ServantClass servantClass) {
        this.servantClass = servantClass;
    }

    public ServantClass getServantClass() {
        return this.servantClass;
    }

    public static class PartyMemberResponseDTO extends ServantResponseDTO {
        private CraftEssenceDTO craftEssence;

        public CraftEssenceDTO getCraftEssence() {
            return this.craftEssence;
        }

        public void setCraftEssence(CraftEssenceDTO craftEssence) {
            this.craftEssence = craftEssence;
        }
    }

}
