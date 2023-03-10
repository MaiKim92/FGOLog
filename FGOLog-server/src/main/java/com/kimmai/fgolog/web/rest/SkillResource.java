package com.kimmai.fgolog.web.rest;

import com.kimmai.fgolog.repository.SkillRepository;
import com.kimmai.fgolog.service.SkillService;
import com.kimmai.fgolog.service.dto.SkillDTO;
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
 * REST controller for managing {@link com.kimmai.fgolog.domain.Skill}.
 */
@RestController
@RequestMapping("/api")
public class SkillResource {

    private final Logger log = LoggerFactory.getLogger(SkillResource.class);

    private static final String ENTITY_NAME = "skill";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SkillService skillService;

    private final SkillRepository skillRepository;

    public SkillResource(SkillService skillService, SkillRepository skillRepository) {
        this.skillService = skillService;
        this.skillRepository = skillRepository;
    }

    /**
     * {@code POST  /admin/admin/skills} : Create a new skill.
     *
     * @param skillDTO the skillDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new skillDTO, or with status {@code 400 (Bad Request)} if the skill has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/admin/skills")
    public ResponseEntity<SkillDTO> createSkill(@RequestBody SkillDTO skillDTO) throws URISyntaxException {
        log.debug("REST request to save Skill : {}", skillDTO);
        if (skillDTO.getId() != null) {
            throw new BadRequestAlertException("A new skill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SkillDTO result = skillService.save(skillDTO);
        return ResponseEntity
            .created(new URI("/api/admin/skills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /admin/skills/:id} : Updates an existing skill.
     *
     * @param id the id of the skillDTO to save.
     * @param skillDTO the skillDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated skillDTO,
     * or with status {@code 400 (Bad Request)} if the skillDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the skillDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/admin/skills/{id}")
    public ResponseEntity<SkillDTO> updateSkill(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SkillDTO skillDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Skill : {}, {}", id, skillDTO);
        if (skillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, skillDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!skillRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SkillDTO result = skillService.save(skillDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, skillDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /admin/skills/:id} : Partial updates given fields of an existing skill, field will ignore if it is null
     *
     * @param id the id of the skillDTO to save.
     * @param skillDTO the skillDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated skillDTO,
     * or with status {@code 400 (Bad Request)} if the skillDTO is not valid,
     * or with status {@code 404 (Not Found)} if the skillDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the skillDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/admin/skills/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SkillDTO> partialUpdateSkill(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SkillDTO skillDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Skill partially : {}, {}", id, skillDTO);
        if (skillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, skillDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!skillRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SkillDTO> result = skillService.partialUpdate(skillDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, skillDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /public/skills} : get all the skills.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of skills in body.
     */
    @GetMapping("/public/skills")
    public List<SkillDTO> getAllSkills() {
        log.debug("REST request to get all Skills");
        return skillService.findAll();
    }

    /**
     * {@code GET  /public/skills-without-servant} : get all the skills that don't belong to a servant
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of skills in body.
     */
    @GetMapping("/public/skills-without-servant")
    public List<SkillDTO> getAllSkillsWithoutServants() {
        log.debug("REST request to get all Skills");
        return skillService.findAllWithoutServantId();
    }

    /**
     * {@code GET  /public/servant-skills/:id} : get all the skills of a servant.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of skills in body.
     */
    @GetMapping("/public/servant-skills/{id}")
    public List<SkillDTO> getSkillsByServantId(@PathVariable Long id) {
        log.debug("REST request to get all Skills");
        return skillService.getByServantId(id);
    }

    /**
     * {@code GET  /admin/skills/:id} : get the "id" skill.
     *
     * @param id the id of the skillDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the skillDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/public/skills/{id}")
    public ResponseEntity<SkillDTO> getSkill(@PathVariable Long id) {
        log.debug("REST request to get Skill : {}", id);
        Optional<SkillDTO> skillDTO = skillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(skillDTO);
    }

    /**
     * {@code DELETE  /admin/skills/:id} : delete the "id" skill.
     *
     * @param id the id of the skillDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/admin/skills/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        log.debug("REST request to delete Skill : {}", id);
        skillService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
