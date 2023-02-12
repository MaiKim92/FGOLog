package com.kimmai.fgolog.service.dto;

import com.kimmai.fgolog.domain.enumeration.CardType;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.kimmai.fgolog.domain.CommandCard} entity.
 */
public class CommandCardDTO implements Serializable {

    private Long id;

    private CardType type;

    private Integer seq;

    private CommandCodeDTO commandCode;

    private ServantDTO servant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public CommandCodeDTO getCommandCode() {
        return commandCode;
    }

    public void setCommandCode(CommandCodeDTO commandCode) {
        this.commandCode = commandCode;
    }

    public ServantDTO getServant() {
        return servant;
    }

    public void setServant(ServantDTO servant) {
        this.servant = servant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandCardDTO)) {
            return false;
        }

        CommandCardDTO commandCardDTO = (CommandCardDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, commandCardDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandCardDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", seq=" + getSeq() +
            ", commandCode=" + getCommandCode() +
            ", servant=" + getServant() +
            "}";
    }
}
