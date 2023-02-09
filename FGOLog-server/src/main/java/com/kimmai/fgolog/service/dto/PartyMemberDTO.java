package com.kimmai.fgolog.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.kimmai.fgolog.domain.PartyMember} entity.
 */
public class PartyMemberDTO implements Serializable {

    private Long id;

    private Integer seq;

    private PartyDTO party;

    private ServantDTO servant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public PartyDTO getParty() {
        return party;
    }

    public void setParty(PartyDTO party) {
        this.party = party;
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
        if (!(o instanceof PartyMemberDTO)) {
            return false;
        }

        PartyMemberDTO partyMemberDTO = (PartyMemberDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, partyMemberDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PartyMemberDTO{" +
            "id=" + getId() +
            ", seq=" + getSeq() +
            ", party=" + getParty() +
            ", servant=" + getServant() +
            "}";
    }
}
