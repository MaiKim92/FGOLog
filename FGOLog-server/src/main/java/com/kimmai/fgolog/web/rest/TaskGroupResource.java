package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.repository.TaskGroupRepository;
import com.kimmai.fgolog.service.TaskGroupService;
import com.kimmai.fgolog.service.dto.TaskGroupDTO;
import com.kimmai.fgolog.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.kimmai.fgolog.domain.TaskGroup}.
 */
@RestController
@RequestMapping("/api")
public class TaskGroupResource {

    private final Logger log = LoggerFactory.getLogger(TaskGroupResource.class);

    private static final String ENTITY_NAME = "taskGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaskGroupService taskGroupService;

    private final TaskGroupRepository taskGroupRepository;

    public TaskGroupResource(TaskGroupService taskGroupService, TaskGroupRepository taskGroupRepository) {
        this.taskGroupService = taskGroupService;
        this.taskGroupRepository = taskGroupRepository;
    }

    /**
     * {@code POST  /admin/task-groups} : Create a new taskGroup.
     *
     * @param taskGroupDTO the taskGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskGroupDTO, or with status {@code 400 (Bad Request)} if the taskGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/task-groups")
    public ResponseEntity<TaskGroupDTO> createTaskGroup(@RequestBody TaskGroupDTO taskGroupDTO) throws URISyntaxException {
        log.debug("REST request to save TaskGroup : {}", taskGroupDTO);
        if (taskGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new taskGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskGroupDTO result = taskGroupService.save(taskGroupDTO);
        return ResponseEntity
            .created(new URI("/api/task-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin/task-groups/:id} : Updates an existing taskGroup.
     *
     * @param id the id of the taskGroupDTO to save.
     * @param taskGroupDTO the taskGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskGroupDTO,
     * or with status {@code 400 (Bad Request)} if the taskGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/task-groups/{id}")
    public ResponseEntity<TaskGroupDTO> updateTaskGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaskGroupDTO taskGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TaskGroup : {}, {}", id, taskGroupDTO);
        if (taskGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taskGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TaskGroupDTO result = taskGroupService.save(taskGroupDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /admin/task-groups/:id} : Partial updates given fields of an existing taskGroup, field will ignore if it is null
     *
     * @param id the id of the taskGroupDTO to save.
     * @param taskGroupDTO the taskGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskGroupDTO,
     * or with status {@code 400 (Bad Request)} if the taskGroupDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taskGroupDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taskGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/admin/task-groups/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaskGroupDTO> partialUpdateTaskGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaskGroupDTO taskGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TaskGroup partially : {}, {}", id, taskGroupDTO);
        if (taskGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taskGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaskGroupDTO> result = taskGroupService.partialUpdate(taskGroupDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskGroupDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /public/task-groups} : get all the taskGroups.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taskGroups in body.
     */
    @GetMapping("/public/task-groups")
    public List<TaskGroupDTO> getAllTaskGroups() {
        log.debug("REST request to get all TaskGroups");
        return taskGroupService.findAll();
    }

    /**
     * {@code GET  /public/task-groups/:id} : get the "id" taskGroup.
     *
     * @param id the id of the taskGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public/task-groups/{id}")
    public ResponseEntity<TaskGroupDTO> getTaskGroup(@PathVariable Long id) {
        log.debug("REST request to get TaskGroup : {}", id);
        Optional<TaskGroupDTO> taskGroupDTO = taskGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskGroupDTO);
    }

    /**
     * {@code DELETE  /admin/task-groups/:id} : delete the "id" taskGroup.
     *
     * @param id the id of the taskGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/task-groups/{id}")
    public ResponseEntity<Void> deleteTaskGroup(@PathVariable Long id) {
        log.debug("REST request to delete TaskGroup : {}", id);
        taskGroupService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
