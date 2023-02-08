package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.MaterialDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.Material}.
 */
public interface MaterialService {
    /**
     * Save a material.
     *
     * @param materialDTO the entity to save.
     * @return the persisted entity.
     */
    MaterialDTO save(MaterialDTO materialDTO);

    /**
     * Partially updates a material.
     *
     * @param materialDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MaterialDTO> partialUpdate(MaterialDTO materialDTO);

    /**
     * Get all the materials.
     *
     * @return the list of entities.
     */
    List<MaterialDTO> findAll();

    /**
     * Get the "id" material.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MaterialDTO> findOne(Long id);

    /**
     * Delete the "id" material.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
