package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.business.WishItemBusiness;
import com.kimmai.fgolog.repository.WishItemRepository;
import com.kimmai.fgolog.service.WishItemService;
import com.kimmai.fgolog.service.dto.WishItemDTO;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.WishItem}.
 */
@RestController
@RequestMapping("/api")
public class WishItemResource {

    private final Logger log = LoggerFactory.getLogger(WishItemResource.class);

    private static final String ENTITY_NAME = "wishItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WishItemService wishItemService;

    private final WishItemRepository wishItemRepository;

    private final WishItemBusiness wishItemBusiness;

    public WishItemResource(WishItemService wishItemService, WishItemRepository wishItemRepository, WishItemBusiness wishItemBusiness) {
        this.wishItemService = wishItemService;
        this.wishItemRepository = wishItemRepository;
        this.wishItemBusiness = wishItemBusiness;
    }

    /**
     * {@code POST  /admin/wish-items} : Create a new wishItem.
     *
     * @param wishItemDTO the wishItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new wishItemDTO, or with status {@code 400 (Bad Request)} if the wishItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/wish-items")
    public ResponseEntity<WishItemDTO> createWishItem(@RequestBody WishItemDTO wishItemDTO) throws URISyntaxException {
        log.debug("REST request to save WishItem : {}", wishItemDTO);
        if (wishItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new wishItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WishItemDTO result = wishItemService.save(wishItemDTO);
        return ResponseEntity
            .created(new URI("/api/admin/wish-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin/wish-items/:id} : Updates an existing wishItem.
     *
     * @param id the id of the wishItemDTO to save.
     * @param servantId the id of the servant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wishItemDTO,
     * or with status {@code 400 (Bad Request)} if the wishItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wishItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/wish-items/{id}/{servantId}")
    public ResponseEntity<WishItemDTO> updateWishItem(
        @PathVariable(value = "id") final Long id,
        @PathVariable(value = "servantId") Long servantId
    ) throws URISyntaxException {
        log.debug("REST request to update WishItem : {}, {}", id, servantId);
        Optional<WishItemDTO> result = wishItemBusiness.update(id, servantId);
        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, id.toString())
        );
    }

    /**
     * {@code PUT  /admin/wish-items/toggle-obtained/:id} : Toggle whether a servant is obtained or not.
     *
     * @param id the id of the wishItemDTO to save.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wishItemDTO,
     * or with status {@code 400 (Bad Request)} if the wishItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the wishItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/wish-items/toggle-obtained/{id}")
    public ResponseEntity<WishItemDTO> updateWishItem(
        @PathVariable(value = "id") final Long id) throws URISyntaxException {
        log.debug("REST request to update WishItem : {}", id);
        Optional<WishItemDTO> result = wishItemBusiness.toggleObtained(id);
        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, id.toString())
        );
    }

    /**
     * {@code PATCH  /admin/wish-items/:id} : Partial updates given fields of an existing wishItem, field will ignore if it is null
     *
     * @param id the id of the wishItemDTO to save.
     * @param wishItemDTO the wishItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated wishItemDTO,
     * or with status {@code 400 (Bad Request)} if the wishItemDTO is not valid,
     * or with status {@code 404 (Not Found)} if the wishItemDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the wishItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/admin/wish-items/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WishItemDTO> partialUpdateWishItem(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody WishItemDTO wishItemDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update WishItem partially : {}, {}", id, wishItemDTO);
        if (wishItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, wishItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!wishItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WishItemDTO> result = wishItemService.partialUpdate(wishItemDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, wishItemDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /public/wish-items} : get all the wishItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wishItems in body.
     */
    @GetMapping("/public/wish-items")
    public List<WishItemDTO> getAllWishItems() {
        log.debug("REST request to get all WishItems");
        return wishItemBusiness.getAllWishItemsForWishList();
    }

    /**
     * {@code GET  /admin/wish-items/:id} : get the "id" wishItem.
     *
     * @param id the id of the wishItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wishItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/admin/wish-items/{id}")
    public ResponseEntity<WishItemDTO> getWishItem(@PathVariable Long id) {
        log.debug("REST request to get WishItem : {}", id);
        Optional<WishItemDTO> wishItemDTO = wishItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(wishItemDTO);
    }

    /**
     * {@code DELETE  /admin/wish-items/:id} : delete the "id" wishItem.
     *
     * @param id the id of the wishItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin/wish-items/{id}")
    public ResponseEntity<Void> deleteWishItem(@PathVariable Long id) {
        log.debug("REST request to delete WishItem : {}", id);
        wishItemService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
