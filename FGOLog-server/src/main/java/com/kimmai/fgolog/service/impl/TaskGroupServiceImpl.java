package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.TaskGroup;
import com.kimmai.fgolog.repository.TaskGroupRepository;
import com.kimmai.fgolog.service.TaskGroupService;
import com.kimmai.fgolog.service.dto.TaskGroupDTO;
import com.kimmai.fgolog.service.mapper.TaskGroupMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TaskGroup}.
 */
@Service
@Transactional
public class TaskGroupServiceImpl implements TaskGroupService {

    private final Logger log = LoggerFactory.getLogger(TaskGroupServiceImpl.class);

    private final TaskGroupRepository taskGroupRepository;

    private final TaskGroupMapper taskGroupMapper;

    public TaskGroupServiceImpl(TaskGroupRepository taskGroupRepository, TaskGroupMapper taskGroupMapper) {
        this.taskGroupRepository = taskGroupRepository;
        this.taskGroupMapper = taskGroupMapper;
    }

    @Override
    public TaskGroupDTO save(TaskGroupDTO taskGroupDTO) {
        log.debug("Request to save TaskGroup : {}", taskGroupDTO);
        TaskGroup taskGroup = taskGroupMapper.toEntity(taskGroupDTO);
        taskGroup = taskGroupRepository.save(taskGroup);
        return taskGroupMapper.toDto(taskGroup);
    }

    @Override
    public Optional<TaskGroupDTO> partialUpdate(TaskGroupDTO taskGroupDTO) {
        log.debug("Request to partially update TaskGroup : {}", taskGroupDTO);

        return taskGroupRepository
            .findById(taskGroupDTO.getId())
            .map(existingTaskGroup -> {
                taskGroupMapper.partialUpdate(existingTaskGroup, taskGroupDTO);

                return existingTaskGroup;
            })
            .map(taskGroupRepository::save)
            .map(taskGroupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskGroupDTO> findAll() {
        log.debug("Request to get all TaskGroups");
        return taskGroupRepository.findAll().stream().map(taskGroupMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaskGroupDTO> findOne(Long id) {
        log.debug("Request to get TaskGroup : {}", id);
        return taskGroupRepository.findById(id).map(taskGroupMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaskGroup : {}", id);
        taskGroupRepository.deleteById(id);
    }
}
