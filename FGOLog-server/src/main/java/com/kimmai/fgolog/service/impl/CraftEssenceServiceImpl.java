package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.CraftEssence;
import com.kimmai.fgolog.repository.CraftEssenceRepository;
import com.kimmai.fgolog.service.CraftEssenceService;
import com.kimmai.fgolog.service.dto.CraftEssenceDTO;
import com.kimmai.fgolog.service.mapper.CraftEssenceMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CraftEssence}.
 */
@Service
@Transactional
public class CraftEssenceServiceImpl implements CraftEssenceService {

    private final Logger log = LoggerFactory.getLogger(CraftEssenceServiceImpl.class);

    private final CraftEssenceRepository craftEssenceRepository;

    private final CraftEssenceMapper craftEssenceMapper;

    public CraftEssenceServiceImpl(CraftEssenceRepository craftEssenceRepository, CraftEssenceMapper craftEssenceMapper) {
        this.craftEssenceRepository = craftEssenceRepository;
        this.craftEssenceMapper = craftEssenceMapper;
    }

    @Override
    public CraftEssenceDTO save(CraftEssenceDTO craftEssenceDTO) {
        log.debug("Request to save CraftEssence : {}", craftEssenceDTO);
        CraftEssence craftEssence = craftEssenceMapper.toEntity(craftEssenceDTO);
        craftEssence = craftEssenceRepository.save(craftEssence);
        return craftEssenceMapper.toDto(craftEssence);
    }

    @Override
    public Optional<CraftEssenceDTO> partialUpdate(CraftEssenceDTO craftEssenceDTO) {
        log.debug("Request to partially update CraftEssence : {}", craftEssenceDTO);

        return craftEssenceRepository
            .findById(craftEssenceDTO.getId())
            .map(existingCraftEssence -> {
                craftEssenceMapper.partialUpdate(existingCraftEssence, craftEssenceDTO);

                return existingCraftEssence;
            })
            .map(craftEssenceRepository::save)
            .map(craftEssenceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CraftEssenceDTO> findAll() {
        log.debug("Request to get all CraftEssences");
        return craftEssenceRepository.findAll().stream().map(craftEssenceMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CraftEssenceDTO> findOne(Long id) {
        log.debug("Request to get CraftEssence : {}", id);
        return craftEssenceRepository.findById(id).map(craftEssenceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CraftEssence : {}", id);
        craftEssenceRepository.deleteById(id);
    }
}
