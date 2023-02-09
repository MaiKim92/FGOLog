package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.Party;
import com.kimmai.fgolog.service.dto.PartyDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Party} and its DTO {@link PartyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PartyMapper extends EntityMapper<PartyDTO, Party> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PartyDTO toDtoId(Party party);
}
