package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.MysticCode;
import com.kimmai.fgolog.repository.MysticCodeRepository;
import com.kimmai.fgolog.service.MysticCodeService;
import com.kimmai.fgolog.service.dto.MysticCodeDTO;
import com.kimmai.fgolog.service.mapper.MysticCodeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MysticCode}.
 */
@Service
@Transactional
public class MysticCodeServiceImpl implements MysticCodeService {

    private final Logger log = LoggerFactory.getLogger(MysticCodeServiceImpl.class);

    private final MysticCodeRepository mysticCodeRepository;

    private final MysticCodeMapper mysticCodeMapper;

    public MysticCodeServiceImpl(MysticCodeRepository mysticCodeRepository, MysticCodeMapper mysticCodeMapper) {
        this.mysticCodeRepository = mysticCodeRepository;
        this.mysticCodeMapper = mysticCodeMapper;
    }

    @Override
    public MysticCodeDTO save(MysticCodeDTO mysticCodeDTO) {
        log.debug("Request to save MysticCode : {}", mysticCodeDTO);
        MysticCode mysticCode = mysticCodeMapper.toEntity(mysticCodeDTO);
        mysticCode = mysticCodeRepository.save(mysticCode);
        return mysticCodeMapper.toDto(mysticCode);
    }

    @Override
    public Optional<MysticCodeDTO> partialUpdate(MysticCodeDTO mysticCodeDTO) {
        log.debug("Request to partially update MysticCode : {}", mysticCodeDTO);

        return mysticCodeRepository
            .findById(mysticCodeDTO.getId())
            .map(existingMysticCode -> {
                mysticCodeMapper.partialUpdate(existingMysticCode, mysticCodeDTO);

                return existingMysticCode;
            })
            .map(mysticCodeRepository::save)
            .map(mysticCodeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MysticCodeDTO> findAll() {
        log.debug("Request to get all MysticCodes");
        return mysticCodeRepository.findAll().stream().map(mysticCodeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MysticCodeDTO> findOne(Long id) {
        log.debug("Request to get MysticCode : {}", id);
        return mysticCodeRepository.findById(id).map(mysticCodeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MysticCode : {}", id);
        mysticCodeRepository.deleteById(id);
    }
}
