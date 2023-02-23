package com.kimmai.fgolog.business;

import com.kimmai.fgolog.service.dto.TaskDTO;
import com.kimmai.fgolog.service.dto.TaskGroupDTO;
import com.kimmai.fgolog.web.rest.dto.TaskGroupResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskBusiness {

    TaskGroupResponseDTO getAllTasks();

    TaskDTO update(TaskDTO taskDTO);
    TaskDTO toggleComplete(Long id);

    TaskDTO increment(Long id);

    List<TaskDTO> getAllTasksForAdmin();
}
