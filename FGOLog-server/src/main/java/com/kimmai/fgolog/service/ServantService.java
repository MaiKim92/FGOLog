package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.ServantDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.Servant}.
 */
public interface ServantService {
    /**
     * Save a servant.
     *
     * @param servantDTO the entity to save.
     * @return the persisted entity.
     */
    ServantDTO save(ServantDTO servantDTO);

    /**
     * Partially updates a servant.
     *
     * @param servantDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ServantDTO> partialUpdate(ServantDTO servantDTO);

    /**
     * Get all the servants.
     *
     * @return the list of entities.
     */
    List<ServantDTO> findAll();

    /**
     * Get all the servants owned.
     *
     * @return the list of entities.
     */
    List<ServantDTO> findAllOwned();

    /**
     * Get the "id" servant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServantDTO> findOne(Long id);

    /**
     * Delete the "id" servant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
