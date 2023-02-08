package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.ServantDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * Get all the servants I own.
     *
     * @return the list of entities.
     */
    List<ServantDTO> findAllOwned();

    /**
     * Get all the servants with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServantDTO> findAllWithEagerRelationships(Pageable pageable);

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
