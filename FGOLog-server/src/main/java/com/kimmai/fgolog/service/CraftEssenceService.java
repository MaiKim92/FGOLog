package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.CraftEssenceDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.CraftEssence}.
 */
public interface CraftEssenceService {
    /**
     * Save a craftEssence.
     *
     * @param craftEssenceDTO the entity to save.
     * @return the persisted entity.
     */
    CraftEssenceDTO save(CraftEssenceDTO craftEssenceDTO);

    /**
     * Partially updates a craftEssence.
     *
     * @param craftEssenceDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CraftEssenceDTO> partialUpdate(CraftEssenceDTO craftEssenceDTO);

    /**
     * Get all the craftEssences.
     *
     * @return the list of entities.
     */
    List<CraftEssenceDTO> findAll();

    /**
     * Get the "id" craftEssence.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CraftEssenceDTO> findOne(Long id);

    /**
     * Delete the "id" craftEssence.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
