package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.PartyMember;
import com.kimmai.fgolog.domain.Servant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data SQL repository for the PartyMember entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PartyMemberRepository extends JpaRepository<PartyMember, Long> {

    @Query(value = "select distinct pm from PartyMember pm where pm.party.id = :pid order by pm.seq asc")
    List<PartyMember> findAllByPartyId(@Param("pid") Long id);
}
