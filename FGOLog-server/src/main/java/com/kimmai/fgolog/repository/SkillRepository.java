package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.Skill;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data SQL repository for the Skill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query("select distinct skill from Skill skill where skill.servant.id = :servantId")
    List<Skill> findAllByServantId(@Param("servantId") Long servantId);
}
