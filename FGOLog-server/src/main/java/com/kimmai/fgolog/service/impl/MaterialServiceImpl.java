package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.Material;
import com.kimmai.fgolog.repository.MaterialRepository;
import com.kimmai.fgolog.service.MaterialService;
import com.kimmai.fgolog.service.dto.MaterialDTO;
import com.kimmai.fgolog.service.mapper.MaterialMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Material}.
 */
@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {

    private final Logger log = LoggerFactory.getLogger(MaterialServiceImpl.class);

    private final MaterialRepository materialRepository;

    private final MaterialMapper materialMapper;

    public MaterialServiceImpl(MaterialRepository materialRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.materialMapper = materialMapper;
    }

    @Override
    public MaterialDTO save(MaterialDTO materialDTO) {
        log.debug("Request to save Material : {}", materialDTO);
        Material material = materialMapper.toEntity(materialDTO);
        material = materialRepository.save(material);
        return materialMapper.toDto(material);
    }

    @Override
    public Optional<MaterialDTO> partialUpdate(MaterialDTO materialDTO) {
        log.debug("Request to partially update Material : {}", materialDTO);

        return materialRepository
            .findById(materialDTO.getId())
            .map(existingMaterial -> {
                materialMapper.partialUpdate(existingMaterial, materialDTO);

                return existingMaterial;
            })
            .map(materialRepository::save)
            .map(materialMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaterialDTO> findAll() {
        log.debug("Request to get all Materials");
        return materialRepository.findAll().stream().map(materialMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MaterialDTO> findOne(Long id) {
        log.debug("Request to get Material : {}", id);
        return materialRepository.findById(id).map(materialMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Material : {}", id);
        materialRepository.deleteById(id);
    }

    @Override
    public Optional<MaterialDTO> increment(Long id) {
        log.debug("Request to increment Material : {}", id);
        MaterialDTO existingMaterial = findOne(id).orElseThrow();
        existingMaterial.setNumber(existingMaterial.getNumber() + 1);
        return partialUpdate(existingMaterial);
    }

    @Override
    public Optional<MaterialDTO> decrement(Long id) {
        log.debug("Request to decrement Material : {}", id);
        MaterialDTO existingMaterial = findOne(id).orElseThrow();
        existingMaterial.setNumber(existingMaterial.getNumber() - 1);
        return partialUpdate(existingMaterial);
    }
}
