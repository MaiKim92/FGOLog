package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.repository.CommandCodeRepository;
import com.kimmai.fgolog.service.CommandCodeService;
import com.kimmai.fgolog.service.dto.CommandCodeDTO;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.CommandCode}.
 */
@RestController
@RequestMapping("/api")
public class CommandCodeResource {

    private final Logger log = LoggerFactory.getLogger(CommandCodeResource.class);

    private static final String ENTITY_NAME = "commandCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommandCodeService commandCodeService;

    private final CommandCodeRepository commandCodeRepository;

    public CommandCodeResource(CommandCodeService commandCodeService, CommandCodeRepository commandCodeRepository) {
        this.commandCodeService = commandCodeService;
        this.commandCodeRepository = commandCodeRepository;
    }

    /**
     * {@code POST  /admin/command-codes} : Create a new commandCode.
     *
     * @param commandCodeDTO the commandCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commandCodeDTO, or with status {@code 400 (Bad Request)} if the commandCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/command-codes")
    public ResponseEntity<CommandCodeDTO> createCommandCode(@RequestBody CommandCodeDTO commandCodeDTO) throws URISyntaxException {
        log.debug("REST request to save CommandCode : {}", commandCodeDTO);
        if (commandCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new commandCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommandCodeDTO result = commandCodeService.save(commandCodeDTO);
        return ResponseEntity
            .created(new URI("/api/command-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin/command-codes/:id} : Updates an existing commandCode.
     *
     * @param id the id of the commandCodeDTO to save.
     * @param commandCodeDTO the commandCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commandCodeDTO,
     * or with status {@code 400 (Bad Request)} if the commandCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commandCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/command-codes/{id}")
    public ResponseEntity<CommandCodeDTO> updateCommandCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CommandCodeDTO commandCodeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CommandCode : {}, {}", id, commandCodeDTO);
        if (commandCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, commandCodeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commandCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CommandCodeDTO result = commandCodeService.save(commandCodeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, commandCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /admin/command-codes/:id} : Partial updates given fields of an existing commandCode, field will ignore if it is null
     *
     * @param id the id of the commandCodeDTO to save.
     * @param commandCodeDTO the commandCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commandCodeDTO,
     * or with status {@code 400 (Bad Request)} if the commandCodeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the commandCodeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the commandCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/admin/command-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CommandCodeDTO> partialUpdateCommandCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CommandCodeDTO commandCodeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CommandCode partially : {}, {}", id, commandCodeDTO);
        if (commandCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, commandCodeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commandCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CommandCodeDTO> result = commandCodeService.partialUpdate(commandCodeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, commandCodeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /public/command-codes} : get all the commandCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commandCodes in body.
     */
    @GetMapping("/public/command-codes")
    public List<CommandCodeDTO> getAllCommandCodes() {
        log.debug("REST request to get all CommandCodes");
        return commandCodeService.findAll();
    }

    /**
     * {@code GET  /public/command-codes/:id} : get the "id" commandCode.
     *
     * @param id the id of the commandCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commandCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public/command-codes/{id}")
    public ResponseEntity<CommandCodeDTO> getCommandCode(@PathVariable Long id) {
        log.debug("REST request to get CommandCode : {}", id);
        Optional<CommandCodeDTO> commandCodeDTO = commandCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commandCodeDTO);
    }

    /**
     * {@code DELETE  /admin/command-codes/:id} : delete the "id" commandCode.
     *
     * @param id the id of the commandCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin/command-codes/{id}")
    public ResponseEntity<Void> deleteCommandCode(@PathVariable Long id) {
        log.debug("REST request to delete CommandCode : {}", id);
        commandCodeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
