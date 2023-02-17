package com.kimmai.fgolog.business;

import com.kimmai.fgolog.service.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskBusiness {

    List<TaskDTO> getAllTasks();

    TaskDTO update(TaskDTO taskDTO);
    TaskDTO toggleComplete(Long id);

    TaskDTO increment(Long id);

}
