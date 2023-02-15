package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.Skill;
import com.kimmai.fgolog.repository.SkillRepository;
import com.kimmai.fgolog.service.SkillService;
import com.kimmai.fgolog.service.dto.SkillDTO;
import com.kimmai.fgolog.service.mapper.SkillMapper;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Skill}.
 */
@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    private final Logger log = LoggerFactory.getLogger(SkillServiceImpl.class);

    private final SkillRepository skillRepository;

    private final SkillMapper skillMapper;

    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public SkillDTO save(SkillDTO skillDTO) {
        log.debug("Request to save Skill : {}", skillDTO);
        Skill skill = skillMapper.toEntity(skillDTO);
        skill = skillRepository.save(skill);
        return skillMapper.toDto(skill);
    }

    @Override
    public Optional<SkillDTO> partialUpdate(SkillDTO skillDTO) {
        log.debug("Request to partially update Skill : {}", skillDTO);

        return skillRepository
            .findById(skillDTO.getId())
            .map(existingSkill -> {
                skillMapper.partialUpdate(existingSkill, skillDTO);

                return existingSkill;
            })
            .map(skillRepository::save)
            .map(skillMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SkillDTO> findAll() {
        log.debug("Request to get all Skills");
        return skillRepository.findAll().stream().map(skillMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SkillDTO> getByServantId(Long servantId) {
        log.debug("Request to get all Skills of servant: {}", servantId);
        List<SkillDTO> result = skillRepository.findAllByServantId(servantId).stream().map(skillMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        result.sort(Comparator.comparing(SkillDTO::getSeq));
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SkillDTO> findOne(Long id) {
        log.debug("Request to get Skill : {}", id);
        return skillRepository.findById(id).map(skillMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Skill : {}", id);
        skillRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SkillDTO> findById(List<Long> ids) {
        log.debug("Request to get Skill : {}", ids);
        return skillRepository.findAllById(ids).stream().map(skillMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SkillDTO> findAllWithoutServantId() {
        log.debug("Request to get all Skills without Servant ID");
        return skillRepository.findAllWithoutServantId().stream().map(skillMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
}
