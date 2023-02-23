package com.kimmai.fgolog.web.rest.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.kimmai.fgolog.service.dto.TaskDTO;
import com.kimmai.fgolog.service.dto.TaskGroupDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskGroupResponseDTO {

    private Integer count;

    private List<TaskResponseDTO> tasks;

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<TaskResponseDTO> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<TaskResponseDTO> tasks) {
        this.tasks = tasks;
    }

    public static class TaskResponseDTO {
        private TaskGroupDTO taskGroup;
        private List<TaskDTO> tasks;

        public TaskGroupDTO getTaskGroup() {
            return this.taskGroup;
        }

        public void setTaskGroup(TaskGroupDTO taskGroup) {
            this.taskGroup = taskGroup;
        }

        public List<TaskDTO> getTasks() {
            return this.tasks;
        }

        public void setTasks(List<TaskDTO> tasks) {
            this.tasks = tasks;
        }
    }

}
