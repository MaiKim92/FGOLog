package com.kimmai.fgolog.service.dto;

import com.kimmai.fgolog.domain.MysticCode;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.kimmai.fgolog.domain.Party} entity.
 */
public class PartyDTO implements Serializable {

    private Long id;

    private String name;

    private MysticCodeDTO mysticCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MysticCodeDTO getMysticCode() {
        return mysticCode;
    }

    public void setMysticCode(MysticCodeDTO mysticCode) {
        this.mysticCode = mysticCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PartyDTO)) {
            return false;
        }

        PartyDTO partyDTO = (PartyDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, partyDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PartyDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
