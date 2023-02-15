package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.business.ServantBusiness;
import com.kimmai.fgolog.repository.ServantRepository;
import com.kimmai.fgolog.service.ServantService;
import com.kimmai.fgolog.service.dto.ServantDTO;
import com.kimmai.fgolog.web.rest.dto.ServantRequestDTO;
import com.kimmai.fgolog.web.rest.dto.ServantResponseDTO;
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

    private final ServantBusiness servantBusiness;

    public ServantResource(ServantService servantService, ServantRepository servantRepository, ServantBusiness servantBusiness) {
        this.servantService = servantService;
        this.servantRepository = servantRepository;
        this.servantBusiness = servantBusiness;
    }

    /**
     * {@code POST  /admin/servants} : Create a new servant.
     *
     * @param request the servantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new servantDTO, or with status {@code 400 (Bad Request)} if the servant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/servants")
    public ResponseEntity<ServantResponseDTO> createServant(@RequestBody ServantRequestDTO request) throws URISyntaxException {
        log.debug("REST request to create Servant : {}", request);
        ServantResponseDTO result = servantBusiness.create(request);
        return ResponseEntity
            .created(new URI("/api/public/servants/" + result.getServant().getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getServant().getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /admin/servants/:id} : update servant.
     *
     * @param request the servantDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new servantDTO, or with status {@code 400 (Bad Request)} if the servant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping("/admin/servants/{id}")
    public ResponseEntity<ServantResponseDTO> updateServant(@PathVariable(value = "id") final Long id, @RequestBody ServantRequestDTO request) throws Exception {
        log.debug("REST request to update Servant : {}, {}", id, request);
        ServantResponseDTO result = servantBusiness.update(id, request);
        return ResponseEntity
            .created(new URI("/api/public/servants/" + result.getServant().getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getServant().getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /admin/servants} : get all the servants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servants in body.
     */
    @GetMapping("/public/servants")
    public List<ServantResponseDTO> getAllServants() {
        log.debug("REST request to get all Servants");
        return servantBusiness.findAll();
    }

    /**
     * {@code GET  /admin/servants-exclude-party/:partyId} : get all the servants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servants in body.
     */
    @GetMapping("/public/servants-exclude-party/{partyId}")
    public List<ServantDTO> getAllServantsExcludeParty(@PathVariable Long partyId) {
        log.debug("REST request to get all Servants exclude party: {}", partyId);
        return servantBusiness.findAllExcludeParty(partyId);
    }

    /**
     * {@code GET  /admin/servants-in-party/:partyId} : get all the servants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servants in body.
     */
    @GetMapping("/public/servants-in-party/{partyId}")
    public List<ServantDTO> getAllServantsInParty(@PathVariable Long partyId) {
        log.debug("REST request to get all Servants exclude party: {}", partyId);
        return servantBusiness.findAllInParty(partyId);
    }

    /**
     * {@code GET  /public/servants-owned} : get all the servants I own.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servants in body.
     */
    @GetMapping("/public/servants-owned")
    public List<ServantResponseDTO> getAllOwnedServants() {
        log.debug("REST request to get all Servants owned");
        return servantBusiness.getAllOwnedServants();
    }

    /**
     * {@code GET  /admin/servants/:id} : get the "id" servant.
     *
     * @param id the id of the servantDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the servantDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public/servants/{id}")
    public ServantResponseDTO getServant(@PathVariable Long id) {
        log.debug("REST request to get Servant : {}", id);
        return servantBusiness.findOne(id);
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
