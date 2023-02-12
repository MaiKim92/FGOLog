package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.CommandCodeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.CommandCode}.
 */
public interface CommandCodeService {
    /**
     * Save a commandCode.
     *
     * @param commandCodeDTO the entity to save.
     * @return the persisted entity.
     */
    CommandCodeDTO save(CommandCodeDTO commandCodeDTO);

    /**
     * Partially updates a commandCode.
     *
     * @param commandCodeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CommandCodeDTO> partialUpdate(CommandCodeDTO commandCodeDTO);

    /**
     * Get all the commandCodes.
     *
     * @return the list of entities.
     */
    List<CommandCodeDTO> findAll();

    /**
     * Get the "id" commandCode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommandCodeDTO> findOne(Long id);

    /**
     * Delete the "id" commandCode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
