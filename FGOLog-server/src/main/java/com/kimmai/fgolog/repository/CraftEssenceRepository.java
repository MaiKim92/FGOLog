package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.CraftEssence;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CraftEssence entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CraftEssenceRepository extends JpaRepository<CraftEssence, Long> {}
