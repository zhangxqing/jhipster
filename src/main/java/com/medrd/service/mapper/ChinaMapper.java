package com.medrd.service.mapper;

import com.medrd.domain.*;
import com.medrd.service.dto.ChinaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity China and its DTO ChinaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChinaMapper extends EntityMapper<ChinaDTO, China> {



    default China fromId(Long id) {
        if (id == null) {
            return null;
        }
        China china = new China();
        china.setId(id);
        return china;
    }
}
