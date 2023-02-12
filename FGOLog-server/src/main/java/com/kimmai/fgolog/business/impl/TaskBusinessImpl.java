package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.TaskBusiness;
import com.kimmai.fgolog.service.MaterialService;
import com.kimmai.fgolog.service.TaskService;
import com.kimmai.fgolog.service.dto.MaterialDTO;
import com.kimmai.fgolog.service.dto.TaskDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskBusinessImpl implements TaskBusiness {

    private final Logger log = LoggerFactory.getLogger(WishItemBusinessImpl.class);

    private final TaskService taskService;

    private final MaterialService materialService;

    public TaskBusinessImpl(TaskService taskService, MaterialService materialService) {
        this.taskService = taskService;
        this.materialService = materialService;
    }

    @Override
    public List<TaskDTO> getAllTasks() {
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
}
