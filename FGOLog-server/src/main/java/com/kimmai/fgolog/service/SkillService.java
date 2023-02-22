package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.SkillDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.Skill}.
 */
public interface SkillService {
    /**
     * Save a skill.
     *
     * @param skillDTO the entity to save.
     * @return the persisted entity.
     */
    SkillDTO save(SkillDTO skillDTO);

    /**
     * Partially updates a skill.
     *
     * @param skillDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SkillDTO> partialUpdate(SkillDTO skillDTO);

    /**
     * Get all the skills.
     *
     * @return the list of entities.
     */
    List<SkillDTO> findAll();

    /**
     * Get all the skills of a servant.
     *
     * @return the list of entities.
     */
    List<SkillDTO> getByServantId(Long servantId);

    /**
     * Get the "id" skill.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SkillDTO> findOne(Long id);

    /**
     * Delete the "id" skill.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get the "id" skills.
     *
     * @param ids the ids of the entity.
     * @return the entity.
     */
    List<SkillDTO> findById(List<Long> ids);

    /**
     * Get all the skills without a servant ID.
     *
     * @return the list of entities.
     */
    List<SkillDTO> findAllWithoutServantId();


    /**
     * Get all the skills with a servant ID.
     *
     * @return the list of entities.
     */
    List<SkillDTO> findAllByServantId(Long servantId);
}
