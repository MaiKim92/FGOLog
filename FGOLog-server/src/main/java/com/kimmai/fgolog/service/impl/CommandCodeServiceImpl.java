package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.CommandCode;
import com.kimmai.fgolog.repository.CommandCodeRepository;
import com.kimmai.fgolog.service.CommandCodeService;
import com.kimmai.fgolog.service.dto.CommandCardDTO;
import com.kimmai.fgolog.service.dto.CommandCodeDTO;
import com.kimmai.fgolog.service.mapper.CommandCodeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CommandCode}.
 */
@Service
@Transactional
public class CommandCodeServiceImpl implements CommandCodeService {

    private final Logger log = LoggerFactory.getLogger(CommandCodeServiceImpl.class);

    private final CommandCodeRepository commandCodeRepository;

    private final CommandCodeMapper commandCodeMapper;

    public CommandCodeServiceImpl(CommandCodeRepository commandCodeRepository, CommandCodeMapper commandCodeMapper) {
        this.commandCodeRepository = commandCodeRepository;
        this.commandCodeMapper = commandCodeMapper;
    }

    @Override
    public CommandCodeDTO save(CommandCodeDTO commandCodeDTO) {
        log.debug("Request to save CommandCode : {}", commandCodeDTO);
        CommandCode commandCode = commandCodeMapper.toEntity(commandCodeDTO);
        commandCode = commandCodeRepository.save(commandCode);
        return commandCodeMapper.toDto(commandCode);
    }

    @Override
    public Optional<CommandCodeDTO> partialUpdate(CommandCodeDTO commandCodeDTO) {
        log.debug("Request to partially update CommandCode : {}", commandCodeDTO);

        return commandCodeRepository
            .findById(commandCodeDTO.getId())
            .map(existingCommandCode -> {
                commandCodeMapper.partialUpdate(existingCommandCode, commandCodeDTO);

                return existingCommandCode;
            })
            .map(commandCodeRepository::save)
            .map(commandCodeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommandCodeDTO> findAll() {
        log.debug("Request to get all CommandCodes");
        return commandCodeRepository.findAll().stream().map(commandCodeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommandCodeDTO> findOne(Long id) {
        log.debug("Request to get CommandCode : {}", id);
        return commandCodeRepository.findById(id).map(commandCodeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommandCode : {}", id);
        commandCodeRepository.deleteById(id);
    }
}
