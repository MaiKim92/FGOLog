package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.CommandCard;
import com.kimmai.fgolog.repository.CommandCardRepository;
import com.kimmai.fgolog.service.CommandCardService;
import com.kimmai.fgolog.service.dto.CommandCardDTO;
import com.kimmai.fgolog.service.mapper.CommandCardMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CommandCard}.
 */
@Service
@Transactional
public class CommandCardServiceImpl implements CommandCardService {

    private final Logger log = LoggerFactory.getLogger(CommandCardServiceImpl.class);

    private final CommandCardRepository commandCardRepository;

    private final CommandCardMapper commandCardMapper;

    public CommandCardServiceImpl(CommandCardRepository commandCardRepository, CommandCardMapper commandCardMapper) {
        this.commandCardRepository = commandCardRepository;
        this.commandCardMapper = commandCardMapper;
    }

    @Override
    public CommandCardDTO save(CommandCardDTO commandCardDTO) {
        log.debug("Request to save CommandCard : {}", commandCardDTO);
        CommandCard commandCard = commandCardMapper.toEntity(commandCardDTO);
        commandCard = commandCardRepository.save(commandCard);
        return commandCardMapper.toDto(commandCard);
    }

    @Override
    public Optional<CommandCardDTO> partialUpdate(CommandCardDTO commandCardDTO) {
        log.debug("Request to partially update CommandCard : {}", commandCardDTO);

        return commandCardRepository
            .findById(commandCardDTO.getId())
            .map(existingCommandCard -> {
                commandCardMapper.partialUpdate(existingCommandCard, commandCardDTO);

                return existingCommandCard;
            })
            .map(commandCardRepository::save)
            .map(commandCardMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommandCardDTO> findAll() {
        log.debug("Request to get all CommandCards");
        return commandCardRepository.findAll().stream().map(commandCardMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommandCardDTO> findOne(Long id) {
        log.debug("Request to get CommandCard : {}", id);
        return commandCardRepository.findById(id).map(commandCardMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommandCard : {}", id);
        commandCardRepository.deleteById(id);
    }

    @Override
    public List<CommandCardDTO> getAllByServantId(Long servantId) {
        log.debug("Request to get all CommandCards by servantId: {}", servantId);
        return commandCardRepository.findAllByServantId(servantId).stream().map(commandCardMapper::toDto).collect(Collectors.toList());
    }
}
