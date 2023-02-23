package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.Task;
import com.kimmai.fgolog.service.dto.TaskDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Task} and its DTO {@link TaskDTO}.
 */
@Mapper(componentModel = "spring", uses = { MaterialMapper.class, TaskGroupMapper.class })
public interface TaskMapper extends EntityMapper<TaskDTO, Task> {
    @Mapping(target = "material", source = "material", qualifiedByName = "id")
    @Mapping(target = "taskGroup", source = "taskGroup", qualifiedByName = "id")
    TaskDTO toDto(Task s);
}
