package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.repository.MysticCodeRepository;
import com.kimmai.fgolog.service.MysticCodeService;
import com.kimmai.fgolog.service.dto.MysticCodeDTO;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.MysticCode}.
 */
@RestController
@RequestMapping("/api")
public class MysticCodeResource {

    private final Logger log = LoggerFactory.getLogger(MysticCodeResource.class);

    private static final String ENTITY_NAME = "mysticCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MysticCodeService mysticCodeService;

    private final MysticCodeRepository mysticCodeRepository;

    public MysticCodeResource(MysticCodeService mysticCodeService, MysticCodeRepository mysticCodeRepository) {
        this.mysticCodeService = mysticCodeService;
        this.mysticCodeRepository = mysticCodeRepository;
    }

    /**
     * {@code POST  admin/mystic-codes} : Create a new mysticCode.
     *
     * @param mysticCodeDTO the mysticCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mysticCodeDTO, or with status {@code 400 (Bad Request)} if the mysticCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/mystic-codes")
    public ResponseEntity<MysticCodeDTO> createMysticCode(@RequestBody MysticCodeDTO mysticCodeDTO) throws URISyntaxException {
        log.debug("REST request to save MysticCode : {}", mysticCodeDTO);
        if (mysticCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mysticCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MysticCodeDTO result = mysticCodeService.save(mysticCodeDTO);
        return ResponseEntity
            .created(new URI("/api/mystic-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  admin/mystic-codes/:id} : Updates an existing mysticCode.
     *
     * @param id the id of the mysticCodeDTO to save.
     * @param mysticCodeDTO the mysticCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mysticCodeDTO,
     * or with status {@code 400 (Bad Request)} if the mysticCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mysticCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/mystic-codes/{id}")
    public ResponseEntity<MysticCodeDTO> updateMysticCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MysticCodeDTO mysticCodeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MysticCode : {}, {}", id, mysticCodeDTO);
        if (mysticCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mysticCodeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mysticCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MysticCodeDTO result = mysticCodeService.save(mysticCodeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mysticCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  admin/mystic-codes/:id} : Partial updates given fields of an existing mysticCode, field will ignore if it is null
     *
     * @param id the id of the mysticCodeDTO to save.
     * @param mysticCodeDTO the mysticCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mysticCodeDTO,
     * or with status {@code 400 (Bad Request)} if the mysticCodeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the mysticCodeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the mysticCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "admin/mystic-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MysticCodeDTO> partialUpdateMysticCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MysticCodeDTO mysticCodeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MysticCode partially : {}, {}", id, mysticCodeDTO);
        if (mysticCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mysticCodeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mysticCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MysticCodeDTO> result = mysticCodeService.partialUpdate(mysticCodeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mysticCodeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  public/mystic-codes} : get all the mysticCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mysticCodes in body.
     */
    @GetMapping("/public/mystic-codes")
    public List<MysticCodeDTO> getAllMysticCodes() {
        log.debug("REST request to get all MysticCodes");
        return mysticCodeService.findAll();
    }

    /**
     * {@code GET  public/mystic-codes/:id} : get the "id" mysticCode.
     *
     * @param id the id of the mysticCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mysticCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public/mystic-codes/{id}")
    public ResponseEntity<MysticCodeDTO> getMysticCode(@PathVariable Long id) {
        log.debug("REST request to get MysticCode : {}", id);
        Optional<MysticCodeDTO> mysticCodeDTO = mysticCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mysticCodeDTO);
    }

    /**
     * {@code DELETE  admin/mystic-codes/:id} : delete the "id" mysticCode.
     *
     * @param id the id of the mysticCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin/mystic-codes/{id}")
    public ResponseEntity<Void> deleteMysticCode(@PathVariable Long id) {
        log.debug("REST request to delete MysticCode : {}", id);
        mysticCodeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
