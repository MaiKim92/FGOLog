package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.CommandCardDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.CommandCard}.
 */
public interface CommandCardService {
    /**
     * Save a commandCard.
     *
     * @param commandCardDTO the entity to save.
     * @return the persisted entity.
     */
    CommandCardDTO save(CommandCardDTO commandCardDTO);

    /**
     * Partially updates a commandCard.
     *
     * @param commandCardDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CommandCardDTO> partialUpdate(CommandCardDTO commandCardDTO);

    /**
     * Get all the commandCards.
     *
     * @return the list of entities.
     */
    List<CommandCardDTO> findAll();

    /**
     * Get the "id" commandCard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommandCardDTO> findOne(Long id);

    /**
     * Delete the "id" commandCard.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the commandCards by servant ID.
     *
     * @return the list of entities.
     */
    List<CommandCardDTO> getAllByServantId(Long servantId);
}
