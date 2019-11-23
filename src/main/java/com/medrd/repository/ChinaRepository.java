package com.medrd.repository;

import com.medrd.domain.China;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the China entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChinaRepository extends JpaRepository<China, Long> {

}
