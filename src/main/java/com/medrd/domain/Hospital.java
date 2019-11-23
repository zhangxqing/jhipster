package com.medrd.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.medrd.domain.enumeration.HospitalType;

import com.medrd.domain.enumeration.HospitalLevel;

/**
 * 医院实体
 */
@ApiModel(description = "医院实体")
@Entity
@Table(name = "hospital")
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    /**
     * 医院等级
     */
    @ApiModelProperty(value = "医院等级")
    @Enumerated(EnumType.STRING)
    @Column(name = "hospital_type")
    private HospitalType hospitalType;

    @Enumerated(EnumType.STRING)
    @Column(name = "hospital_level")
    private HospitalLevel hospitalLevel;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Size(max = 255)
    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Lob
    @Column(name = "intro")
    private String intro;

    @ManyToOne
    @JsonIgnoreProperties("")
    private China cityId;

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

    public Hospital name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HospitalType getHospitalType() {
        return hospitalType;
    }

    public Hospital hospitalType(HospitalType hospitalType) {
        this.hospitalType = hospitalType;
        return this;
    }

    public void setHospitalType(HospitalType hospitalType) {
        this.hospitalType = hospitalType;
    }

    public HospitalLevel getHospitalLevel() {
        return hospitalLevel;
    }

    public Hospital hospitalLevel(HospitalLevel hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
        return this;
    }

    public void setHospitalLevel(HospitalLevel hospitalLevel) {
        this.hospitalLevel = hospitalLevel;
    }

    public String getAddress() {
        return address;
    }

    public Hospital address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public Hospital phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Hospital imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntro() {
        return intro;
    }

    public Hospital intro(String intro) {
        this.intro = intro;
        return this;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public China getCityId() {
        return cityId;
    }

    public Hospital cityId(China china) {
        this.cityId = china;
        return this;
    }

    public void setCityId(China china) {
        this.cityId = china;
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
        Hospital hospital = (Hospital) o;
        if (hospital.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hospital.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Hospital{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", hospitalType='" + getHospitalType() + "'" +
            ", hospitalLevel='" + getHospitalLevel() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", intro='" + getIntro() + "'" +
            "}";
    }
}
