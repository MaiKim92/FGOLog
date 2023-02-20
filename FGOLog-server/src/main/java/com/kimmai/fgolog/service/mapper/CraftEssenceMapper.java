package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.CraftEssence;
import com.kimmai.fgolog.service.dto.CraftEssenceDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CraftEssence} and its DTO {@link CraftEssenceDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CraftEssenceMapper extends EntityMapper<CraftEssenceDTO, CraftEssence> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CraftEssenceDTO toDtoId(CraftEssence craftEssence);
}
