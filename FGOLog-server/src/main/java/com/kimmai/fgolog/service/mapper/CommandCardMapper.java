package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.CommandCard;
import com.kimmai.fgolog.service.dto.CommandCardDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CommandCard} and its DTO {@link CommandCardDTO}.
 */
@Mapper(componentModel = "spring", uses = { CommandCodeMapper.class, ServantMapper.class })
public interface CommandCardMapper extends EntityMapper<CommandCardDTO, CommandCard> {
    @Mapping(target = "commandCode", source = "commandCode", qualifiedByName = "id")
    @Mapping(target = "servant", source = "servant", qualifiedByName = "id")
    CommandCardDTO toDto(CommandCard s);
}
