package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.PartyMember;
import com.kimmai.fgolog.repository.PartyMemberRepository;
import com.kimmai.fgolog.service.PartyMemberService;
import com.kimmai.fgolog.service.dto.PartyMemberDTO;
import com.kimmai.fgolog.service.mapper.PartyMemberMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link PartyMember}.
 */
@Service
@Transactional
public class PartyMemberServiceImpl implements PartyMemberService {

    private final Logger log = LoggerFactory.getLogger(PartyMemberServiceImpl.class);

    private final PartyMemberRepository partyMemberRepository;

    private final PartyMemberMapper partyMemberMapper;

    public PartyMemberServiceImpl(PartyMemberRepository partyMemberRepository, PartyMemberMapper partyMemberMapper) {
        this.partyMemberRepository = partyMemberRepository;
        this.partyMemberMapper = partyMemberMapper;
    }

    @Override
    public PartyMemberDTO save(PartyMemberDTO partyMemberDTO) {
        log.debug("Request to save PartyMember : {}", partyMemberDTO);
        PartyMember partyMember = partyMemberMapper.toEntity(partyMemberDTO);
        partyMember = partyMemberRepository.save(partyMember);
        return partyMemberMapper.toDto(partyMember);
    }

    @Override
    public Optional<PartyMemberDTO> partialUpdate(PartyMemberDTO partyMemberDTO) {
        log.debug("Request to partially update PartyMember : {}", partyMemberDTO);

        return partyMemberRepository
            .findById(partyMemberDTO.getId())
            .map(existingPartyMember -> {
                partyMemberMapper.partialUpdate(existingPartyMember, partyMemberDTO);

                return existingPartyMember;
            })
            .map(partyMemberRepository::save)
            .map(partyMemberMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartyMemberDTO> findAll() {
        log.debug("Request to get all PartyMembers");
        return partyMemberRepository.findAll().stream().map(partyMemberMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PartyMemberDTO> findOne(Long id) {
        log.debug("Request to get PartyMember : {}", id);
        return partyMemberRepository.findById(id).map(partyMemberMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PartyMember : {}", id);
        partyMemberRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> findAllServantIdsByPartyId(Long partyId) {
        log.debug("Request to get all Servant Ids by Party Id");
        return partyMemberRepository.getAllServantIdsByPartyId(partyId);
    }
}
