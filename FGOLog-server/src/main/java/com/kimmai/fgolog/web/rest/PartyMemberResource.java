package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.repository.PartyMemberRepository;
import com.kimmai.fgolog.service.PartyMemberService;
import com.kimmai.fgolog.service.dto.PartyMemberDTO;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.PartyMember}.
 */
@RestController
@RequestMapping("/api")
public class PartyMemberResource {

    private final Logger log = LoggerFactory.getLogger(PartyMemberResource.class);

    private static final String ENTITY_NAME = "partyMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PartyMemberService partyMemberService;

    private final PartyMemberRepository partyMemberRepository;

    public PartyMemberResource(PartyMemberService partyMemberService, PartyMemberRepository partyMemberRepository) {
        this.partyMemberService = partyMemberService;
        this.partyMemberRepository = partyMemberRepository;
    }

    /**
     * {@code POST  /party-members} : Create a new partyMember.
     *
     * @param partyMemberDTO the partyMemberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new partyMemberDTO, or with status {@code 400 (Bad Request)} if the partyMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/party-members")
    public ResponseEntity<PartyMemberDTO> createPartyMember(@RequestBody PartyMemberDTO partyMemberDTO) throws URISyntaxException {
        log.debug("REST request to save PartyMember : {}", partyMemberDTO);
        if (partyMemberDTO.getId() != null) {
            throw new BadRequestAlertException("A new partyMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PartyMemberDTO result = partyMemberService.save(partyMemberDTO);
        return ResponseEntity
            .created(new URI("/api/party-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /party-members/:id} : Updates an existing partyMember.
     *
     * @param id the id of the partyMemberDTO to save.
     * @param partyMemberDTO the partyMemberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated partyMemberDTO,
     * or with status {@code 400 (Bad Request)} if the partyMemberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the partyMemberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/party-members/{id}")
    public ResponseEntity<PartyMemberDTO> updatePartyMember(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PartyMemberDTO partyMemberDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PartyMember : {}, {}", id, partyMemberDTO);
        if (partyMemberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, partyMemberDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!partyMemberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PartyMemberDTO result = partyMemberService.save(partyMemberDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, partyMemberDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /party-members/:id} : Partial updates given fields of an existing partyMember, field will ignore if it is null
     *
     * @param id the id of the partyMemberDTO to save.
     * @param partyMemberDTO the partyMemberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated partyMemberDTO,
     * or with status {@code 400 (Bad Request)} if the partyMemberDTO is not valid,
     * or with status {@code 404 (Not Found)} if the partyMemberDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the partyMemberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/party-members/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PartyMemberDTO> partialUpdatePartyMember(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PartyMemberDTO partyMemberDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PartyMember partially : {}, {}", id, partyMemberDTO);
        if (partyMemberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, partyMemberDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!partyMemberRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PartyMemberDTO> result = partyMemberService.partialUpdate(partyMemberDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, partyMemberDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /party-members} : get all the partyMembers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of partyMembers in body.
     */
    @GetMapping("/party-members")
    public List<PartyMemberDTO> getAllPartyMembers() {
        log.debug("REST request to get all PartyMembers");
        return partyMemberService.findAll();
    }

    /**
     * {@code GET  /party-members/:id} : get the "id" partyMember.
     *
     * @param id the id of the partyMemberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the partyMemberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/party-members/{id}")
    public ResponseEntity<PartyMemberDTO> getPartyMember(@PathVariable Long id) {
        log.debug("REST request to get PartyMember : {}", id);
        Optional<PartyMemberDTO> partyMemberDTO = partyMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(partyMemberDTO);
    }

    /**
     * {@code DELETE  /party-members/:id} : delete the "id" partyMember.
     *
     * @param id the id of the partyMemberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/party-members/{id}")
    public ResponseEntity<Void> deletePartyMember(@PathVariable Long id) {
        log.debug("REST request to delete PartyMember : {}", id);
        partyMemberService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
