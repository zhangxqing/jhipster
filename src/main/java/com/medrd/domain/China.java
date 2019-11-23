package com.medrd.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A China.
 */
@Entity
@Table(name = "china")
public class China implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 25)
    @Column(name = "name", length = 25)
    private String name;

    @Column(name = "p_id")
    private Long pId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public China name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getpId() {
        return pId;
    }

    public China pId(Long pId) {
        this.pId = pId;
        return this;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        China china = (China) o;
        if (china.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), china.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "China{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", pId=" + getpId() +
            "}";
    }
}
