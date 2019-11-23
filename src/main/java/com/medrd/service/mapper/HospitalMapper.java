package com.medrd.service.mapper;

import com.medrd.domain.*;
import com.medrd.service.dto.HospitalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Hospital and its DTO HospitalDTO.
 */
@Mapper(componentModel = "spring", uses = {ChinaMapper.class})
public interface HospitalMapper extends EntityMapper<HospitalDTO, Hospital> {

    @Mapping(source = "cityId.id", target = "cityIdId")
    HospitalDTO toDto(Hospital hospital);

    @Mapping(source = "cityIdId", target = "cityId")
    Hospital toEntity(HospitalDTO hospitalDTO);

    default Hospital fromId(Long id) {
        if (id == null) {
            return null;
        }
        Hospital hospital = new Hospital();
        hospital.setId(id);
        return hospital;
    }
}
