package com.kimmai.fgolog.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * A DTO for the {@link com.kimmai.fgolog.domain.CommandCode} entity.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandCodeDTO implements Serializable {

    private Long id;

    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandCodeDTO)) {
            return false;
        }

        CommandCodeDTO commandCodeDTO = (CommandCodeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, commandCodeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandCodeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
