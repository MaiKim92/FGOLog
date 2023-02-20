package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.CraftEssence;
import com.kimmai.fgolog.domain.MysticCode;
import com.kimmai.fgolog.service.dto.CraftEssenceDTO;
import com.kimmai.fgolog.service.dto.MysticCodeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MysticCode} and its DTO {@link MysticCodeDTO}.
 */
@Mapper(componentModel = "spring", uses = { PartyMapper.class })
public interface MysticCodeMapper extends EntityMapper<MysticCodeDTO, MysticCode> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MysticCodeDTO toDtoId(MysticCode mysticCode);
}
