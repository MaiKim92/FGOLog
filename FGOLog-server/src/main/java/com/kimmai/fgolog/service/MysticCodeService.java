package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.MysticCodeDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.MysticCode}.
 */
public interface MysticCodeService {
    /**
     * Save a mysticCode.
     *
     * @param mysticCodeDTO the entity to save.
     * @return the persisted entity.
     */
    MysticCodeDTO save(MysticCodeDTO mysticCodeDTO);

    /**
     * Partially updates a mysticCode.
     *
     * @param mysticCodeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<MysticCodeDTO> partialUpdate(MysticCodeDTO mysticCodeDTO);

    /**
     * Get all the mysticCodes.
     *
     * @return the list of entities.
     */
    List<MysticCodeDTO> findAll();

    /**
     * Get the "id" mysticCode.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MysticCodeDTO> findOne(Long id);

    /**
     * Delete the "id" mysticCode.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
