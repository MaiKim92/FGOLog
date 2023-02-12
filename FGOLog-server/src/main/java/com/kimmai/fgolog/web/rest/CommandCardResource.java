package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.repository.CommandCardRepository;
import com.kimmai.fgolog.service.CommandCardService;
import com.kimmai.fgolog.service.dto.CommandCardDTO;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.CommandCard}.
 */
@RestController
@RequestMapping("/api")
public class CommandCardResource {

    private final Logger log = LoggerFactory.getLogger(CommandCardResource.class);

    private static final String ENTITY_NAME = "commandCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommandCardService commandCardService;

    private final CommandCardRepository commandCardRepository;

    public CommandCardResource(CommandCardService commandCardService, CommandCardRepository commandCardRepository) {
        this.commandCardService = commandCardService;
        this.commandCardRepository = commandCardRepository;
    }

    /**
     * {@code POST  /command-cards} : Create a new commandCard.
     *
     * @param commandCardDTO the commandCardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commandCardDTO, or with status {@code 400 (Bad Request)} if the commandCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/command-cards")
    public ResponseEntity<CommandCardDTO> createCommandCard(@RequestBody CommandCardDTO commandCardDTO) throws URISyntaxException {
        log.debug("REST request to save CommandCard : {}", commandCardDTO);
        if (commandCardDTO.getId() != null) {
            throw new BadRequestAlertException("A new commandCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommandCardDTO result = commandCardService.save(commandCardDTO);
        return ResponseEntity
            .created(new URI("/api/command-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /command-cards/:id} : Updates an existing commandCard.
     *
     * @param id the id of the commandCardDTO to save.
     * @param commandCardDTO the commandCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commandCardDTO,
     * or with status {@code 400 (Bad Request)} if the commandCardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commandCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/command-cards/{id}")
    public ResponseEntity<CommandCardDTO> updateCommandCard(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CommandCardDTO commandCardDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CommandCard : {}, {}", id, commandCardDTO);
        if (commandCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, commandCardDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commandCardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CommandCardDTO result = commandCardService.save(commandCardDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, commandCardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /command-cards/:id} : Partial updates given fields of an existing commandCard, field will ignore if it is null
     *
     * @param id the id of the commandCardDTO to save.
     * @param commandCardDTO the commandCardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commandCardDTO,
     * or with status {@code 400 (Bad Request)} if the commandCardDTO is not valid,
     * or with status {@code 404 (Not Found)} if the commandCardDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the commandCardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/command-cards/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CommandCardDTO> partialUpdateCommandCard(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CommandCardDTO commandCardDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CommandCard partially : {}, {}", id, commandCardDTO);
        if (commandCardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, commandCardDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commandCardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CommandCardDTO> result = commandCardService.partialUpdate(commandCardDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, commandCardDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /command-cards} : get all the commandCards.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commandCards in body.
     */
    @GetMapping("/command-cards")
    public List<CommandCardDTO> getAllCommandCards() {
        log.debug("REST request to get all CommandCards");
        return commandCardService.findAll();
    }

    /**
     * {@code GET  /command-cards/:id} : get the "id" commandCard.
     *
     * @param id the id of the commandCardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commandCardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/command-cards/{id}")
    public ResponseEntity<CommandCardDTO> getCommandCard(@PathVariable Long id) {
        log.debug("REST request to get CommandCard : {}", id);
        Optional<CommandCardDTO> commandCardDTO = commandCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(commandCardDTO);
    }

    /**
     * {@code DELETE  /command-cards/:id} : delete the "id" commandCard.
     *
     * @param id the id of the commandCardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/command-cards/{id}")
    public ResponseEntity<Void> deleteCommandCard(@PathVariable Long id) {
        log.debug("REST request to delete CommandCard : {}", id);
        commandCardService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
