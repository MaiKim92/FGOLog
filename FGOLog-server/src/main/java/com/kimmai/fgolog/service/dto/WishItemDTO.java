package com.kimmai.fgolog.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.kimmai.fgolog.domain.WishItem} entity.
 */
public class WishItemDTO implements Serializable {

    private Long id;

    private ServantDTO servant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(o instanceof WishItemDTO)) {
            return false;
        }

        WishItemDTO wishItemDTO = (WishItemDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, wishItemDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WishItemDTO{" +
            "id=" + getId() +
            ", servant=" + getServant() +
            "}";
    }
}
