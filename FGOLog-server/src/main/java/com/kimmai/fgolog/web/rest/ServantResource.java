package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.repository.ServantRepository;
import com.kimmai.fgolog.service.ServantService;
import com.kimmai.fgolog.service.dto.ServantDTO;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.Servant}.
 */
@RestController
@RequestMapping("/api")
public class ServantResource {

    private final Logger log = LoggerFactory.getLogger(ServantResource.class);

    private static final String ENTITY_NAME = "servant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServantService servantService;

    private final ServantRepository servantRepository;

    public ServantResource(ServantService servantService, ServantRepository servantRepository) {
        this.servantService = servantService;
        this.servantRepository = servantRepository;
    }

    /**
     * {@code POST  /admin/servants} : Create a new servant.
     *
     * @param servantDTO the servantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new servantDTO, or with status {@code 400 (Bad Request)} if the servant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/servants")
    public ResponseEntity<ServantDTO> createServant(@RequestBody ServantDTO servantDTO) throws URISyntaxException {
        log.debug("REST request to save Servant : {}", servantDTO);
        if (servantDTO.getId() != null) {
            throw new BadRequestAlertException("A new servant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServantDTO result = servantService.save(servantDTO);
        return ResponseEntity
            .created(new URI("/api/servants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin/servants/:id} : Updates an existing servant.
     *
     * @param id the id of the servantDTO to save.
     * @param servantDTO the servantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated servantDTO,
     * or with status {@code 400 (Bad Request)} if the servantDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the servantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/servants/{id}")
    public ResponseEntity<ServantDTO> updateServant(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ServantDTO servantDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Servant : {}, {}", id, servantDTO);
        if (servantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, servantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!servantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ServantDTO result = servantService.save(servantDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, servantDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /admin/servants/:id} : Partial updates given fields of an existing servant, field will ignore if it is null
     *
     * @param id the id of the servantDTO to save.
     * @param servantDTO the servantDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated servantDTO,
     * or with status {@code 400 (Bad Request)} if the servantDTO is not valid,
     * or with status {@code 404 (Not Found)} if the servantDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the servantDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/admin/servants/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ServantDTO> partialUpdateServant(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ServantDTO servantDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Servant partially : {}, {}", id, servantDTO);
        if (servantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, servantDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!servantRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ServantDTO> result = servantService.partialUpdate(servantDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, servantDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /admin/servants} : get all the servants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servants in body.
     */
    @GetMapping("/servants")
    public List<ServantDTO> getAllServants() {
        log.debug("REST request to get all Servants");
        return servantService.findAll();
    }

    /**
     * {@code GET  /public/servants-owned} : get all the servants I own.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servants in body.
     */
    @GetMapping("/public/servants-owned")
    public List<ServantDTO> getAllOwnedServants() {
        log.debug("REST request to get all Servants owned");
        return servantService.findAllOwned();
    }

    /**
     * {@code GET  /admin/servants/:id} : get the "id" servant.
     *
     * @param id the id of the servantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the servantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public/servants/{id}")
    public ResponseEntity<ServantDTO> getServant(@PathVariable Long id) {
        log.debug("REST request to get Servant : {}", id);
        Optional<ServantDTO> servantDTO = servantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(servantDTO);
    }

    /**
     * {@code DELETE  /admin/servants/:id} : delete the "id" servant.
     *
     * @param id the id of the servantDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin/servants/{id}")
    public ResponseEntity<Void> deleteServant(@PathVariable Long id) {
        log.debug("REST request to delete Servant : {}", id);
        servantService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
