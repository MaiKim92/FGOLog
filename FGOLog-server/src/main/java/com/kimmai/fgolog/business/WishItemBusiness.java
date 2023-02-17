package com.kimmai.fgolog.business;

import com.kimmai.fgolog.service.dto.WishItemDTO;

import java.util.List;
import java.util.Optional;

public interface WishItemBusiness {

    List<WishItemDTO> getAllWishItemsForWishList();

    Optional<WishItemDTO> update(Long id, Long servantId);

    Optional<WishItemDTO> toggleObtained(Long id);
}
