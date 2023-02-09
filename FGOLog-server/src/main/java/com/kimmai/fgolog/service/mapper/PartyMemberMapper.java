package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.PartyMember;
import com.kimmai.fgolog.service.dto.PartyMemberDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PartyMember} and its DTO {@link PartyMemberDTO}.
 */
@Mapper(componentModel = "spring", uses = { PartyMapper.class, ServantMapper.class })
public interface PartyMemberMapper extends EntityMapper<PartyMemberDTO, PartyMember> {
    @Mapping(target = "party", source = "party", qualifiedByName = "id")
    @Mapping(target = "servant", source = "servant", qualifiedByName = "id")
    PartyMemberDTO toDto(PartyMember s);
}
