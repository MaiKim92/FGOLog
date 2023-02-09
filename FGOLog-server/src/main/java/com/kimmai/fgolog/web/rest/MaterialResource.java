package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.repository.MaterialRepository;
import com.kimmai.fgolog.service.MaterialService;
import com.kimmai.fgolog.service.dto.MaterialDTO;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.Material}.
 */
@RestController
@RequestMapping("/api")
public class MaterialResource {

    private final Logger log = LoggerFactory.getLogger(MaterialResource.class);

    private static final String ENTITY_NAME = "material";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MaterialService materialService;

    private final MaterialRepository materialRepository;

    public MaterialResource(MaterialService materialService, MaterialRepository materialRepository) {
        this.materialService = materialService;
        this.materialRepository = materialRepository;
    }

    /**
     * {@code POST  /admin/materials} : Create a new material.
     *
     * @param materialDTO the materialDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new materialDTO, or with status {@code 400 (Bad Request)} if the material has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/materials")
    public ResponseEntity<MaterialDTO> createMaterial(@RequestBody MaterialDTO materialDTO) throws URISyntaxException {
        log.debug("REST request to save Material : {}", materialDTO);
        if (materialDTO.getId() != null) {
            throw new BadRequestAlertException("A new material cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MaterialDTO result = materialService.save(materialDTO);
        return ResponseEntity
            .created(new URI("/api/admin/materials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin/materials/:id} : Updates an existing material.
     *
     * @param id the id of the materialDTO to save.
     * @param materialDTO the materialDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated materialDTO,
     * or with status {@code 400 (Bad Request)} if the materialDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the materialDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/materials/{id}")
    public ResponseEntity<MaterialDTO> updateMaterial(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MaterialDTO materialDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Material : {}, {}", id, materialDTO);
        if (materialDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, materialDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!materialRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MaterialDTO result = materialService.save(materialDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, materialDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /admin/materials/:id} : Partial updates given fields of an existing material, field will ignore if it is null
     *
     * @param id the id of the materialDTO to save.
     * @param materialDTO the materialDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated materialDTO,
     * or with status {@code 400 (Bad Request)} if the materialDTO is not valid,
     * or with status {@code 404 (Not Found)} if the materialDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the materialDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/admin/materials/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MaterialDTO> partialUpdateMaterial(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MaterialDTO materialDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Material partially : {}, {}", id, materialDTO);
        if (materialDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, materialDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!materialRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MaterialDTO> result = materialService.partialUpdate(materialDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, materialDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /public/materials} : get all the materials.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of materials in body.
     */
    @GetMapping("/public/materials")
    public List<MaterialDTO> getAllMaterials() {
        log.debug("REST request to get all Materials");
        return materialService.findAll();
    }

    /**
     * {@code GET  /admin/materials/:id} : get the "id" material.
     *
     * @param id the id of the materialDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the materialDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public/materials/{id}")
    public ResponseEntity<MaterialDTO> getMaterial(@PathVariable Long id) {
        log.debug("REST request to get Material : {}", id);
        Optional<MaterialDTO> materialDTO = materialService.findOne(id);
        return ResponseUtil.wrapOrNotFound(materialDTO);
    }

    /**
     * {@code DELETE  /admin/materials/:id} : delete the "id" material.
     *
     * @param id the id of the materialDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin/materials/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        log.debug("REST request to delete Material : {}", id);
        materialService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
