package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.Servant;
import com.kimmai.fgolog.service.dto.ServantDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Servant} and its DTO {@link ServantDTO}.
 */
@Mapper(componentModel = "spring", uses = { PartyMapper.class })
public interface ServantMapper extends EntityMapper<ServantDTO, Servant> {
    @Mapping(target = "parties", source = "parties", qualifiedByName = "idSet")
    ServantDTO toDto(Servant s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServantDTO toDtoId(Servant servant);

    @Mapping(target = "removeParty", ignore = true)
    Servant toEntity(ServantDTO servantDTO);
}
