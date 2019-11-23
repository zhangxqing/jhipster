package com.medrd.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the China entity.
 */
public class ChinaDTO implements Serializable {

    private Long id;

    @Size(max = 25)
    private String name;

    private Long pId;

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

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChinaDTO chinaDTO = (ChinaDTO) o;
        if (chinaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chinaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChinaDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", pId=" + getpId() +
            "}";
    }
}
