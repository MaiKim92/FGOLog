package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.business.PartyBusiness;
import com.kimmai.fgolog.repository.PartyRepository;
import com.kimmai.fgolog.service.PartyService;
import com.kimmai.fgolog.service.dto.PartyDTO;
import com.kimmai.fgolog.web.rest.dto.PartyRequestDTO;
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
     * @param req the request.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new partyDTO, or with status {@code 400 (Bad Request)} if the party has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/parties")
    public ResponseEntity<PartyResponseDTO> createParty(@RequestBody PartyRequestDTO req) throws URISyntaxException {
        log.debug("REST request to save Party : {}", req);
        PartyResponseDTO result = partyBusiness.create(req);
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
    @PatchMapping("/admin/parties/{id}")
    public ResponseEntity<PartyResponseDTO> updateParty(
        @PathVariable(value = "id") final Long id,
        @RequestBody PartyRequestDTO req
    ) throws Exception {
        log.debug("REST request to save Party : {}", req);
        PartyResponseDTO result = partyBusiness.update(id, req);
        return ResponseEntity
            .created(new URI("/api/admin/parties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /public/parties} : get all the parties.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parties in body.
     */
    @GetMapping("/public/parties")
    public List<PartyResponseDTO> getAllParties() {
        log.debug("REST request to get all Parties");
        return partyBusiness.getAllParties();
    }

    /**
     * {@code GET  /public/parties/:id} : get the "id" party.
     *
     * @param id the id of the partyDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the partyDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public/parties/{id}")
    public ResponseEntity<PartyResponseDTO> getParty(@PathVariable Long id) {
        log.debug("REST request to get Party : {}", id);
        Optional<PartyResponseDTO> party = partyBusiness.findOne(id);
        return ResponseUtil.wrapOrNotFound(party);
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
