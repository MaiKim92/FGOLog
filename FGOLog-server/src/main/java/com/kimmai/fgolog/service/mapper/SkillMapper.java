package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.Skill;
import com.kimmai.fgolog.service.dto.SkillDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Skill} and its DTO {@link SkillDTO}.
 */
@Mapper(componentModel = "spring", uses = { ServantMapper.class })
public interface SkillMapper extends EntityMapper<SkillDTO, Skill> {
    @Mapping(target = "servant", source = "servant", qualifiedByName = "id")
    SkillDTO toDto(Skill s);
}
