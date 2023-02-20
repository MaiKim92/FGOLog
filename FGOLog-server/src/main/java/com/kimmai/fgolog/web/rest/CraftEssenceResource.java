package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.repository.CraftEssenceRepository;
import com.kimmai.fgolog.service.CraftEssenceService;
import com.kimmai.fgolog.service.dto.CraftEssenceDTO;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.CraftEssence}.
 */
@RestController
@RequestMapping("/api")
public class CraftEssenceResource {

    private final Logger log = LoggerFactory.getLogger(CraftEssenceResource.class);

    private static final String ENTITY_NAME = "craftEssence";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CraftEssenceService craftEssenceService;

    private final CraftEssenceRepository craftEssenceRepository;

    public CraftEssenceResource(CraftEssenceService craftEssenceService, CraftEssenceRepository craftEssenceRepository) {
        this.craftEssenceService = craftEssenceService;
        this.craftEssenceRepository = craftEssenceRepository;
    }

    /**
     * {@code POST  /admin/craft-essences} : Create a new craftEssence.
     *
     * @param craftEssenceDTO the craftEssenceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new craftEssenceDTO, or with status {@code 400 (Bad Request)} if the craftEssence has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/craft-essences")
    public ResponseEntity<CraftEssenceDTO> createCraftEssence(@RequestBody CraftEssenceDTO craftEssenceDTO) throws URISyntaxException {
        log.debug("REST request to save CraftEssence : {}", craftEssenceDTO);
        if (craftEssenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new craftEssence cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CraftEssenceDTO result = craftEssenceService.save(craftEssenceDTO);
        return ResponseEntity
            .created(new URI("/api/craft-essences/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  admin/craft-essences/:id} : Updates an existing craftEssence.
     *
     * @param id the id of the craftEssenceDTO to save.
     * @param craftEssenceDTO the craftEssenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated craftEssenceDTO,
     * or with status {@code 400 (Bad Request)} if the craftEssenceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the craftEssenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/craft-essences/{id}")
    public ResponseEntity<CraftEssenceDTO> updateCraftEssence(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CraftEssenceDTO craftEssenceDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CraftEssence : {}, {}", id, craftEssenceDTO);
        if (craftEssenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, craftEssenceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!craftEssenceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CraftEssenceDTO result = craftEssenceService.save(craftEssenceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, craftEssenceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  admin/craft-essences/:id} : Partial updates given fields of an existing craftEssence, field will ignore if it is null
     *
     * @param id the id of the craftEssenceDTO to save.
     * @param craftEssenceDTO the craftEssenceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated craftEssenceDTO,
     * or with status {@code 400 (Bad Request)} if the craftEssenceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the craftEssenceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the craftEssenceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "admin/craft-essences/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CraftEssenceDTO> partialUpdateCraftEssence(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CraftEssenceDTO craftEssenceDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CraftEssence partially : {}, {}", id, craftEssenceDTO);
        if (craftEssenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, craftEssenceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!craftEssenceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CraftEssenceDTO> result = craftEssenceService.partialUpdate(craftEssenceDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, craftEssenceDTO.getId().toString())
        );
    }

    /**
     * {@code GET  public/craft-essences} : get all the craftEssences.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of craftEssences in body.
     */
    @GetMapping("/public/craft-essences")
    public List<CraftEssenceDTO> getAllCraftEssences() {
        log.debug("REST request to get all CraftEssences");
        return craftEssenceService.findAll();
    }

    /**
     * {@code GET  public/craft-essences/:id} : get the "id" craftEssence.
     *
     * @param id the id of the craftEssenceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the craftEssenceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public/craft-essences/{id}")
    public ResponseEntity<CraftEssenceDTO> getCraftEssence(@PathVariable Long id) {
        log.debug("REST request to get CraftEssence : {}", id);
        Optional<CraftEssenceDTO> craftEssenceDTO = craftEssenceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(craftEssenceDTO);
    }

    /**
     * {@code DELETE  admin/craft-essences/:id} : delete the "id" craftEssence.
     *
     * @param id the id of the craftEssenceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin/craft-essences/{id}")
    public ResponseEntity<Void> deleteCraftEssence(@PathVariable Long id) {
        log.debug("REST request to delete CraftEssence : {}", id);
        craftEssenceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
