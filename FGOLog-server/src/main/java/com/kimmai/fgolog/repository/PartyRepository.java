package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.Party;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Party entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {}
