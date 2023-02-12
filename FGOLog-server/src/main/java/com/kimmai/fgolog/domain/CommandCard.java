package com.kimmai.fgolog.domain;

import com.kimmai.fgolog.domain.enumeration.CardType;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A CommandCard.
 */
@Entity
@Table(name = "command_card")
public class CommandCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CardType type;

    @Column(name = "seq")
    private Integer seq;

    @OneToOne
    @JoinColumn(unique = true)
    private CommandCode commandCode;

    @ManyToOne
    private Servant servant;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CommandCard id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CardType getType() {
        return this.type;
    }

    public CommandCard type(CardType type) {
        this.setType(type);
        return this;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public CommandCard seq(Integer seq) {
        this.setSeq(seq);
        return this;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public CommandCode getCommandCode() {
        return this.commandCode;
    }

    public void setCommandCode(CommandCode commandCode) {
        this.commandCode = commandCode;
    }

    public CommandCard commandCode(CommandCode commandCode) {
        this.setCommandCode(commandCode);
        return this;
    }

    public Servant getServant() {
        return this.servant;
    }

    public void setServant(Servant servant) {
        this.servant = servant;
    }

    public CommandCard servant(Servant servant) {
        this.setServant(servant);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandCard)) {
            return false;
        }
        return id != null && id.equals(((CommandCard) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandCard{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", seq=" + getSeq() +
            "}";
    }
}
