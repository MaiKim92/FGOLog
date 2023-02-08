package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.PartyDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.Party}.
 */
public interface PartyService {
    /**
     * Save a party.
     *
     * @param partyDTO the entity to save.
     * @return the persisted entity.
     */
    PartyDTO save(PartyDTO partyDTO);

    /**
     * Partially updates a party.
     *
     * @param partyDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PartyDTO> partialUpdate(PartyDTO partyDTO);

    /**
     * Get all the parties.
     *
     * @return the list of entities.
     */
    List<PartyDTO> findAll();

    /**
     * Get the "id" party.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PartyDTO> findOne(Long id);

    /**
     * Delete the "id" party.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
