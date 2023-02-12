package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.CommandCode;
import com.kimmai.fgolog.service.dto.CommandCodeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommandCode} and its DTO {@link CommandCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CommandCodeMapper extends EntityMapper<CommandCodeDTO, CommandCode> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CommandCodeDTO toDtoId(CommandCode commandCode);
}
