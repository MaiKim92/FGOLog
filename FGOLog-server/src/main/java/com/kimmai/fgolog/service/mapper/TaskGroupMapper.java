package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.TaskGroup;
import com.kimmai.fgolog.service.dto.TaskGroupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TaskGroup} and its DTO {@link TaskGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaskGroupMapper extends EntityMapper<TaskGroupDTO, TaskGroup> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TaskGroupDTO toDtoId(TaskGroup taskGroup);
}
