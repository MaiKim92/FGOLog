package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.MysticCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MysticCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MysticCodeRepository extends JpaRepository<MysticCode, Long> {}
