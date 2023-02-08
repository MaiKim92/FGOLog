package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.Party;
import com.kimmai.fgolog.repository.PartyRepository;
import com.kimmai.fgolog.service.PartyService;
import com.kimmai.fgolog.service.dto.PartyDTO;
import com.kimmai.fgolog.service.mapper.PartyMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Party}.
 */
@Service
@Transactional
public class PartyServiceImpl implements PartyService {

    private final Logger log = LoggerFactory.getLogger(PartyServiceImpl.class);

    private final PartyRepository partyRepository;

    private final PartyMapper partyMapper;

    public PartyServiceImpl(PartyRepository partyRepository, PartyMapper partyMapper) {
        this.partyRepository = partyRepository;
        this.partyMapper = partyMapper;
    }

    @Override
    public PartyDTO save(PartyDTO partyDTO) {
        log.debug("Request to save Party : {}", partyDTO);
        Party party = partyMapper.toEntity(partyDTO);
        party = partyRepository.save(party);
        return partyMapper.toDto(party);
    }

    @Override
    public Optional<PartyDTO> partialUpdate(PartyDTO partyDTO) {
        log.debug("Request to partially update Party : {}", partyDTO);

        return partyRepository
            .findById(partyDTO.getId())
            .map(existingParty -> {
                partyMapper.partialUpdate(existingParty, partyDTO);

                return existingParty;
            })
            .map(partyRepository::save)
            .map(partyMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartyDTO> findAll() {
        log.debug("Request to get all Parties");
        return partyRepository.findAll().stream().map(partyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PartyDTO> findOne(Long id) {
        log.debug("Request to get Party : {}", id);
        return partyRepository.findById(id).map(partyMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Party : {}", id);
        partyRepository.deleteById(id);
    }
}
