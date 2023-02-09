package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.Servant;
import com.kimmai.fgolog.service.dto.ServantDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Servant} and its DTO {@link ServantDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServantMapper extends EntityMapper<ServantDTO, Servant> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServantDTO toDtoId(Servant servant);
}
