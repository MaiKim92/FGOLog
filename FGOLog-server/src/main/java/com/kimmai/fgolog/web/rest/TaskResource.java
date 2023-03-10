package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.business.TaskBusiness;
import com.kimmai.fgolog.repository.TaskRepository;
import com.kimmai.fgolog.service.TaskService;
import com.kimmai.fgolog.service.dto.TaskDTO;
import com.kimmai.fgolog.service.dto.TaskGroupDTO;
import com.kimmai.fgolog.web.rest.dto.TaskGroupResponseDTO;
import com.kimmai.fgolog.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.Task}.
 */
@RestController
@RequestMapping("/api")
public class TaskResource {

    private final Logger log = LoggerFactory.getLogger(TaskResource.class);

    private static final String ENTITY_NAME = "task";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaskService taskService;

    private final TaskRepository taskRepository;

    private final TaskBusiness taskBusiness;

    public TaskResource(TaskService taskService, TaskRepository taskRepository, TaskBusiness taskBusiness) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.taskBusiness = taskBusiness;
    }

    /**
     * {@code POST  /admin/tasks} : Create a new task.
     *
     * @param taskDTO the taskDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taskDTO, or with status {@code 400 (Bad Request)} if the task has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/tasks")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) throws URISyntaxException {
        log.debug("REST request to save Task : {}", taskDTO);
        if (taskDTO.getId() != null) {
            throw new BadRequestAlertException("A new task cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaskDTO result = taskService.save(taskDTO);
        return ResponseEntity
            .created(new URI("/api/admin/tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin/tasks/:id} : Updates an existing task.
     *
     * @param id the id of the taskDTO to save.
     * @param taskDTO the taskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskDTO,
     * or with status {@code 400 (Bad Request)} if the taskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable(value = "id") final Long id, @RequestBody TaskDTO taskDTO)
        throws URISyntaxException {

        TaskDTO result = taskBusiness.update(taskDTO);
        return ResponseEntity
            .ok()
            .body(result);
    }

    /**
     * {@code PUT  /admin/tasks/toggle-completed/:id} : Updates an existing task to completed or in progress
     *
     * @param id the id of the taskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskDTO,
     * or with status {@code 400 (Bad Request)} if the taskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/tasks/toggle-completed/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable(value = "id") final Long id)
        throws URISyntaxException {

        TaskDTO result = taskBusiness.toggleComplete(id);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /admin/tasks/increment/:id} : Updates an existing task to completed or in progress
     *
     * @param id the id of the taskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskDTO,
     * or with status {@code 400 (Bad Request)} if the taskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping("/admin/tasks/increment/{id}")
    public ResponseEntity<TaskDTO> increment(@PathVariable(value = "id") final Long id)
        throws URISyntaxException {

        TaskDTO result = taskBusiness.increment(id);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /admin/tasks/:id} : Partial updates given fields of an existing task, field will ignore if it is null
     *
     * @param id the id of the taskDTO to save.
     * @param taskDTO the taskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taskDTO,
     * or with status {@code 400 (Bad Request)} if the taskDTO is not valid,
     * or with status {@code 404 (Not Found)} if the taskDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the taskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/admin/tasks/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TaskDTO> partialUpdateTask(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TaskDTO taskDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Task partially : {}, {}", id, taskDTO);
        if (taskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taskDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taskRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TaskDTO> result = taskService.partialUpdate(taskDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taskDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /public/tasks} : get all the tasks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tasks in body.
     */
    @GetMapping("/public/tasks")
    public TaskGroupResponseDTO getAllTasks() {
        log.debug("REST request to get all Tasks");
        return taskBusiness.getAllTasks();
    }

    /**
     * {@code GET  /public/all-tasks} : get all the tasks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tasks in body.
     */
    @GetMapping("/public/all-tasks")
    public List<TaskDTO> getAllTasksForAdmin() {
        log.debug("REST request to get all Tasks");
        return taskBusiness.getAllTasksForAdmin();
    }

    /**
     * {@code GET  /admin/tasks/:id} : get the "id" task.
     *
     * @param id the id of the taskDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taskDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public/tasks/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id) {
        log.debug("REST request to get Task : {}", id);
        Optional<TaskDTO> taskDTO = taskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taskDTO);
    }

    /**
     * {@code DELETE  /admin/tasks/:id} : delete the "id" task.
     *
     * @param id the id of the taskDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        log.debug("REST request to delete Task : {}", id);
        taskService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
