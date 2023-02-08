package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.Servant;
import com.kimmai.fgolog.repository.ServantRepository;
import com.kimmai.fgolog.service.ServantService;
import com.kimmai.fgolog.service.dto.ServantDTO;
import com.kimmai.fgolog.service.mapper.ServantMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Servant}.
 */
@Service
@Transactional
public class ServantServiceImpl implements ServantService {

    private final Logger log = LoggerFactory.getLogger(ServantServiceImpl.class);

    private final ServantRepository servantRepository;

    private final ServantMapper servantMapper;

    public ServantServiceImpl(ServantRepository servantRepository, ServantMapper servantMapper) {
        this.servantRepository = servantRepository;
        this.servantMapper = servantMapper;
    }

    @Override
    public ServantDTO save(ServantDTO servantDTO) {
        log.debug("Request to save Servant : {}", servantDTO);
        Servant servant = servantMapper.toEntity(servantDTO);
        servant = servantRepository.save(servant);
        return servantMapper.toDto(servant);
    }

    @Override
    public Optional<ServantDTO> partialUpdate(ServantDTO servantDTO) {
        log.debug("Request to partially update Servant : {}", servantDTO);

        return servantRepository
            .findById(servantDTO.getId())
            .map(existingServant -> {
                servantMapper.partialUpdate(existingServant, servantDTO);

                return existingServant;
            })
            .map(servantRepository::save)
            .map(servantMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServantDTO> findAll() {
        log.debug("Request to get all Servants");
        return servantRepository
            .findAllWithEagerRelationships()
            .stream()
            .map(servantMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServantDTO> findAllOwned() {
        log.debug("Request to get all Servants");
        return servantRepository
            .findAllByIsHasWithEagerRelationships(true)
            .stream()
            .map(servantMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<ServantDTO> findAllWithEagerRelationships(Pageable pageable) {
        return servantRepository.findAllWithEagerRelationships(pageable).map(servantMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServantDTO> findOne(Long id) {
        log.debug("Request to get Servant : {}", id);
        return servantRepository.findOneWithEagerRelationships(id).map(servantMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Servant : {}", id);
        servantRepository.deleteById(id);
    }
}
