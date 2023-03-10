package com.kimmai.fgolog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Party.
 */
@Entity
@Table(name = "party")
public class Party implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties(value = { "party" }, allowSetters = true)
    private MysticCode mysticCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Party id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MysticCode getMysticCode() {
        return this.mysticCode;
    }

    public void setMysticCode(MysticCode mysticCode) {
        this.mysticCode = mysticCode;
    }

    public Party mysticCode(MysticCode mysticCode) {
        setMysticCode(mysticCode);
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Party name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Party)) {
            return false;
        }
        return id != null && id.equals(((Party) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Party{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
