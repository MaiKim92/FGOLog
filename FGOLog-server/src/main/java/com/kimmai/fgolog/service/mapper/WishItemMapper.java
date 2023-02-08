package com.kimmai.fgolog.service.mapper;

import com.kimmai.fgolog.domain.WishItem;
import com.kimmai.fgolog.service.dto.WishItemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WishItem} and its DTO {@link WishItemDTO}.
 */
@Mapper(componentModel = "spring", uses = { ServantMapper.class })
public interface WishItemMapper extends EntityMapper<WishItemDTO, WishItem> {
    @Mapping(target = "servant", source = "servant", qualifiedByName = "id")
    WishItemDTO toDto(WishItem s);
}
