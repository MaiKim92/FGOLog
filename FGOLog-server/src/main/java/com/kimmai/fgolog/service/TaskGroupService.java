package com.kimmai.fgolog.service;

import com.kimmai.fgolog.service.dto.TaskGroupDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.kimmai.fgolog.domain.TaskGroup}.
 */
public interface TaskGroupService {
    /**
     * Save a taskGroup.
     *
     * @param taskGroupDTO the entity to save.
     * @return the persisted entity.
     */
    TaskGroupDTO save(TaskGroupDTO taskGroupDTO);

    /**
     * Partially updates a taskGroup.
     *
     * @param taskGroupDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TaskGroupDTO> partialUpdate(TaskGroupDTO taskGroupDTO);

    /**
     * Get all the taskGroups.
     *
     * @return the list of entities.
     */
    List<TaskGroupDTO> findAll();

    /**
     * Get the "id" taskGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaskGroupDTO> findOne(Long id);

    /**
     * Delete the "id" taskGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
