package com.medrd.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import com.medrd.domain.enumeration.HospitalType;
import com.medrd.domain.enumeration.HospitalLevel;

/**
 * A DTO for the Hospital entity.
 */
public class HospitalDTO implements Serializable {

    private Long id;

    @Size(max = 50)
    private String name;

    private HospitalType hospitalType;

    private HospitalLevel hospitalLevel;

    private String address;

    private String phone;

    @Size(max = 255)
    private String imageUrl;

    @Lob
    private String intro;

    private Long cityIdId;

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

    public HospitalType getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(HospitalType hospitalType) {
        this.hospitalType = hospitalType;
    }

    public HospitalLevel getHospitalLevel() {
        return hospitalLevel;
    }

    public void setHospitalLevel(HospitalLevel hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Long getCityIdId() {
        return cityIdId;
    }

    public void setCityIdId(Long chinaId) {
        this.cityIdId = chinaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HospitalDTO hospitalDTO = (HospitalDTO) o;
        if (hospitalDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hospitalDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HospitalDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", hospitalType='" + getHospitalType() + "'" +
            ", hospitalLevel='" + getHospitalLevel() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", intro='" + getIntro() + "'" +
            ", cityId=" + getCityIdId() +
            "}";
    }
}
