package com.kimmai.fgolog.business.impl;

import com.kimmai.fgolog.business.WishItemBusiness;
import com.kimmai.fgolog.service.ServantService;
import com.kimmai.fgolog.service.WishItemService;
import com.kimmai.fgolog.service.dto.ServantDTO;
import com.kimmai.fgolog.service.dto.WishItemDTO;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WishItemBusinessImpl implements WishItemBusiness {

    private final Logger log = LoggerFactory.getLogger(WishItemBusinessImpl.class);

    private final WishItemService wishItemService;

    private final ServantService servantService;

    public WishItemBusinessImpl(WishItemService wishItemService, ServantService servantService) {
        this.wishItemService = wishItemService;
        this.servantService = servantService;
    }

    @Override
    public List<WishItemDTO> getAllWishItemsForWishList() {
        try {
            List<WishItemDTO> wishItems = wishItemService.findAll();
            wishItems.forEach(item -> {
                ServantDTO servant = servantService.findOne(item.getServant().getId()).orElseThrow();
                item.setServant(servant);
            });
            return wishItems;
        } catch (Exception e) {
            log.debug("Get all wish items failed");
            throw e;
        }
    }
}
