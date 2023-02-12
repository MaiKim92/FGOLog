package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.business.PartyBusiness;
import com.kimmai.fgolog.repository.PartyRepository;
import com.kimmai.fgolog.service.PartyService;
import com.kimmai.fgolog.service.dto.PartyDTO;
import com.kimmai.fgolog.web.rest.dto.PartyResponseDTO;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.Party}.
 */
@RestController
@RequestMapping("/api")
public class PartyResource {

    private final Logger log = LoggerFactory.getLogger(PartyResource.class);

    private static final String ENTITY_NAME = "party";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PartyService partyService;

    private final PartyRepository partyRepository;

    private final PartyBusiness partyBusiness;

    public PartyResource(PartyService partyService, PartyRepository partyRepository, PartyBusiness partyBusiness) {
        this.partyService = partyService;
        this.partyRepository = partyRepository;
        this.partyBusiness = partyBusiness;
    }

    /**
     * {@code POST  /admin/parties} : Create a new party.
     *
     * @param partyDTO the partyDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new partyDTO, or with status {@code 400 (Bad Request)} if the party has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/parties")
    public ResponseEntity<PartyDTO> createParty(@RequestBody PartyDTO partyDTO) throws URISyntaxException {
        log.debug("REST request to save Party : {}", partyDTO);
        if (partyDTO.getId() != null) {
            throw new BadRequestAlertException("A new party cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PartyDTO result = partyService.save(partyDTO);
        return ResponseEntity
            .created(new URI("/api/admin/parties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin/parties/:id} : Updates an existing party.
     *
     * @param id the id of the partyDTO to save.
     * @param partyDTO the partyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated partyDTO,
     * or with status {@code 400 (Bad Request)} if the partyDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the partyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/parties/{id}")
    public ResponseEntity<PartyDTO> updateParty(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PartyDTO partyDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Party : {}, {}", id, partyDTO);
        if (partyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, partyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!partyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PartyDTO result = partyService.save(partyDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, partyDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /admin/parties/:id} : Partial updates given fields of an existing party, field will ignore if it is null
     *
     * @param id the id of the partyDTO to save.
     * @param partyDTO the partyDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated partyDTO,
     * or with status {@code 400 (Bad Request)} if the partyDTO is not valid,
     * or with status {@code 404 (Not Found)} if the partyDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the partyDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/admin/parties/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PartyDTO> partialUpdateParty(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PartyDTO partyDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Party partially : {}, {}", id, partyDTO);
        if (partyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, partyDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!partyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PartyDTO> result = partyService.partialUpdate(partyDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, partyDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /admin/parties} : get all the parties.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parties in body.
     */
    @GetMapping("/public/parties")
    public List<PartyResponseDTO> getAllParties() {
        log.debug("REST request to get all Parties");
        return partyBusiness.getAllParties();
    }

    /**
     * {@code GET  /admin/parties/:id} : get the "id" party.
     *
     * @param id the id of the partyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the partyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admin/parties/{id}")
    public ResponseEntity<PartyDTO> getParty(@PathVariable Long id) {
        log.debug("REST request to get Party : {}", id);
        Optional<PartyDTO> partyDTO = partyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(partyDTO);
    }

    /**
     * {@code DELETE  /admin/parties/:id} : delete the "id" party.
     *
     * @param id the id of the partyDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin/parties/{id}")
    public ResponseEntity<Void> deleteParty(@PathVariable Long id) {
        log.debug("REST request to delete Party : {}", id);
        partyService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
