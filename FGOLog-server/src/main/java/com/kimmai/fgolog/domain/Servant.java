package com.kimmai.fgolog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Servant.
 */
@Entity
@Table(name = "servant")
public class Servant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private Integer level;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "is_has")
    private Boolean isHas;

    @ManyToMany
    @JoinTable(
        name = "rel_servant__party",
        joinColumns = @JoinColumn(name = "servant_id"),
        inverseJoinColumns = @JoinColumn(name = "party_id")
    )
    @JsonIgnoreProperties(value = { "servants" }, allowSetters = true)
    private Set<Party> parties = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Servant id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Servant name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return this.level;
    }

    public Servant level(Integer level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Servant imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public Servant thumbnailUrl(String thumbnailUrl) {
        this.setThumbnailUrl(thumbnailUrl);
        return this;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Boolean getIsHas() {
        return this.isHas;
    }

    public Servant isHas(Boolean isHas) {
        this.setIsHas(isHas);
        return this;
    }

    public void setIsHas(Boolean isHas) {
        this.isHas = isHas;
    }

    public Set<Party> getParties() {
        return this.parties;
    }

    public void setParties(Set<Party> parties) {
        this.parties = parties;
    }

    public Servant parties(Set<Party> parties) {
        this.setParties(parties);
        return this;
    }

    public Servant addParty(Party party) {
        this.parties.add(party);
        party.getServants().add(this);
        return this;
    }

    public Servant removeParty(Party party) {
        this.parties.remove(party);
        party.getServants().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Servant)) {
            return false;
        }
        return id != null && id.equals(((Servant) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Servant{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", level=" + getLevel() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", thumbnailUrl='" + getThumbnailUrl() + "'" +
            ", isHas='" + getIsHas() + "'" +
            "}";
    }
}
