package com.kimmai.fgolog.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A PartyMember.
 */
@Entity
@Table(name = "party_member")
public class PartyMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "seq")
    private Integer seq;

    @JsonIgnoreProperties(value = { "mysticCodes" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Party party;

    @ManyToOne
    private Servant servant;

    @ManyToOne
    private CraftEssence craftEssence;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PartyMember id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public PartyMember seq(Integer seq) {
        this.setSeq(seq);
        return this;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Party getParty() {
        return this.party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public PartyMember party(Party party) {
        this.setParty(party);
        return this;
    }

    public Servant getServant() {
        return this.servant;
    }

    public void setServant(Servant servant) {
        this.servant = servant;
    }

    public PartyMember servant(Servant servant) {
        this.setServant(servant);
        return this;
    }

    public CraftEssence getCraftEssence() {
        return this.craftEssence;
    }

    public void setCraftEssence(CraftEssence craftEssence) {
        this.craftEssence = craftEssence;
    }

    public PartyMember craftEssence(CraftEssence craftEssence) {
        this.setCraftEssence(craftEssence);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PartyMember)) {
            return false;
        }
        return id != null && id.equals(((PartyMember) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PartyMember{" +
            "id=" + getId() +
            ", seq=" + getSeq() +
            "}";
    }
}
