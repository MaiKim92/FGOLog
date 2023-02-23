package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.TaskBusiness;
import com.kimmai.fgolog.domain.Task;
import com.kimmai.fgolog.domain.enumeration.TaskStatus;
import com.kimmai.fgolog.service.MaterialService;
import com.kimmai.fgolog.service.TaskGroupService;
import com.kimmai.fgolog.service.TaskService;
import com.kimmai.fgolog.service.dto.MaterialDTO;
import com.kimmai.fgolog.service.dto.TaskDTO;
import com.kimmai.fgolog.service.dto.TaskGroupDTO;
import com.kimmai.fgolog.web.rest.dto.TaskGroupResponseDTO;
import com.kimmai.fgolog.web.rest.dto.TaskGroupResponseDTO.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaskBusinessImpl implements TaskBusiness {

    private final Logger log = LoggerFactory.getLogger(WishItemBusinessImpl.class);

    private final TaskService taskService;

    private final MaterialService materialService;

    private final TaskGroupService taskGroupService;

    public TaskBusinessImpl(TaskService taskService, MaterialService materialService, TaskGroupService taskGroupService) {
        this.taskService = taskService;
        this.materialService = materialService;
        this.taskGroupService = taskGroupService;
    }

    @Override
    public TaskGroupResponseDTO getAllTasks() {
        try {
            List<TaskGroupDTO> taskGroups = taskGroupService.findAll();
            List<TaskDTO> tasks = taskService.findAll();
            TaskGroupResponseDTO response = new TaskGroupResponseDTO();
            tasks.forEach(task -> {
                MaterialDTO material = materialService.findOne(task.getMaterial().getId()).orElseThrow();
                task.setMaterial(material);
            });
            Integer count = 0;
            List<TaskResponseDTO> taskResponses = new ArrayList<>();
            for (TaskGroupDTO taskGroup : taskGroups) {
                count++;
                TaskResponseDTO task = new TaskResponseDTO();
                task.setTaskGroup(taskGroup);
                task.setTasks(tasks.stream().filter(t -> t.getTaskGroup().getId() == taskGroup.getId()).collect(Collectors.toList()));
                taskResponses.add(task);
            }
            response.setCount(count);
            response.setTasks(taskResponses);
            return response;
        } catch (Exception e) {
            log.debug("Get all tasks failed");
            throw e;
        }
    }

    @Override
    public List<TaskDTO> getAllTasksForAdmin() {
        try {
            List<TaskDTO> tasks = taskService.findAll();
            tasks.forEach(task -> {
                MaterialDTO material = materialService.findOne(task.getMaterial().getId()).orElseThrow();
                task.setMaterial(material);
            });
            return tasks;
        } catch (Exception e) {
            log.debug("Get all tasks failed");
            throw e;
        }
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO) {
        TaskDTO existingTask = taskService.findOne(taskDTO.getId()).orElseThrow();
        TaskGroupDTO existingTaskGroup = taskGroupService.findOne(taskDTO.getTaskGroup().getId()).orElseThrow();
        MaterialDTO materialDTO = materialService.findOne(taskDTO.getMaterial().getId()).orElseThrow();
        taskDTO.setMaterial(materialDTO);
        taskDTO.setStatus(existingTask.getStatus());
        taskDTO.setTaskGroup(existingTaskGroup);
        return taskService.save(taskDTO);
    }

    public TaskDTO toggleComplete(Long id) {
        TaskDTO existingTask = taskService.findOne(id).orElseThrow();
        if (existingTask.getStatus().equals(TaskStatus.IN_PROGRESS)) {
            existingTask.setStatus(TaskStatus.COMPLETED);
        } else {
            existingTask.setStatus(TaskStatus.IN_PROGRESS);
        }
        return taskService.partialUpdate(existingTask).orElseThrow();
    }

    public TaskDTO increment(Long id) {
        TaskDTO existingTask = taskService.findOne(id).orElseThrow();
        existingTask.setProgress(existingTask.getProgress() + 1);
        return taskService.partialUpdate(existingTask).orElseThrow();
    }
}
