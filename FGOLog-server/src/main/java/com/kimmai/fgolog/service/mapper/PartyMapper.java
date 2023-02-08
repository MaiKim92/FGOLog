package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.Party;
import com.kimmai.fgolog.service.dto.PartyDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Party} and its DTO {@link PartyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PartyMapper extends EntityMapper<PartyDTO, Party> {
    @Named("idSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    Set<PartyDTO> toDtoIdSet(Set<Party> party);
}
