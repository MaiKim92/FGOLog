package com.kimmai.fgolog.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.kimmai.fgolog.domain.Servant} entity.
 */
public class ServantDTO implements Serializable {

    private Long id;

    private String name;

    private Integer level;

    private String imageUrl;

    private String thumbnailUrl;

    private Boolean isHas;

    private Set<PartyDTO> parties = new HashSet<>();

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Boolean getIsHas() {
        return isHas;
    }

    public void setIsHas(Boolean isHas) {
        this.isHas = isHas;
    }

    public Set<PartyDTO> getParties() {
        return parties;
    }

    public void setParties(Set<PartyDTO> parties) {
        this.parties = parties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServantDTO)) {
            return false;
        }

        ServantDTO servantDTO = (ServantDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, servantDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServantDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", level=" + getLevel() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", thumbnailUrl='" + getThumbnailUrl() + "'" +
            ", isHas='" + getIsHas() + "'" +
            ", parties=" + getParties() +
            "}";
    }
}
