package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.WishItemDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.WishItem}.
 */
public interface WishItemService {
    /**
     * Save a wishItem.
     *
     * @param wishItemDTO the entity to save.
     * @return the persisted entity.
     */
    WishItemDTO save(WishItemDTO wishItemDTO);

    /**
     * Partially updates a wishItem.
     *
     * @param wishItemDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WishItemDTO> partialUpdate(WishItemDTO wishItemDTO);

    /**
     * Get all the wishItems.
     *
     * @return the list of entities.
     */
    List<WishItemDTO> findAll();

    /**
     * Get the "id" wishItem.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WishItemDTO> findOne(Long id);

    /**
     * Delete the "id" wishItem.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
