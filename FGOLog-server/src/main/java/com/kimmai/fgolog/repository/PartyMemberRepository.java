package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.PartyMember;
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

    @Query(value = "select distinct pm.servant.id from PartyMember pm where pm.party.id = :pid order by pm.seq asc")
    List<Long> getAllServantIdsByPartyId(@Param("pid") Long id);
}
