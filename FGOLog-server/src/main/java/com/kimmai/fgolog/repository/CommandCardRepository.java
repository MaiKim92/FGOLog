package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.CommandCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data SQL repository for the CommandCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommandCardRepository extends JpaRepository<CommandCard, Long> {

    @Query(value = "select distinct cc from CommandCard cc where cc.servant.id = :sid order by cc.seq asc")
    List<CommandCard> findAllByServantId(@Param("sid") Long servantId);
}
