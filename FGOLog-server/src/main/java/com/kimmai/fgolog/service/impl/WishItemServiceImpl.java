package com.kimmai.fgolog.service.impl;

import com.kimmai.fgolog.domain.Servant;
import com.kimmai.fgolog.domain.WishItem;
import com.kimmai.fgolog.repository.WishItemRepository;
import com.kimmai.fgolog.service.WishItemService;
import com.kimmai.fgolog.service.dto.WishItemDTO;
import com.kimmai.fgolog.service.mapper.WishItemMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link WishItem}.
 */
@Service
@Transactional
public class WishItemServiceImpl implements WishItemService {

    private final Logger log = LoggerFactory.getLogger(WishItemServiceImpl.class);

    private final WishItemRepository wishItemRepository;

    private final WishItemMapper wishItemMapper;

    public WishItemServiceImpl(WishItemRepository wishItemRepository, WishItemMapper wishItemMapper) {
        this.wishItemRepository = wishItemRepository;
        this.wishItemMapper = wishItemMapper;
    }

    @Override
    public WishItemDTO save(WishItemDTO wishItemDTO) {
        log.debug("Request to save WishItem : {}", wishItemDTO);
        WishItem wishItem = wishItemMapper.toEntity(wishItemDTO);
        wishItem = wishItemRepository.save(wishItem);
        return wishItemMapper.toDto(wishItem);
    }

    @Override
    public Optional<WishItemDTO> partialUpdate(WishItemDTO wishItemDTO) {
        log.debug("Request to partially update WishItem : {}", wishItemDTO);

        return wishItemRepository
            .findById(wishItemDTO.getId())
            .map(existingWishItem -> {
                wishItemMapper.partialUpdate(existingWishItem, wishItemDTO);

                return existingWishItem;
            })
            .map(wishItemRepository::save)
            .map(wishItemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WishItemDTO> findAll() {
        log.debug("Request to get all WishItems");
        return wishItemRepository.findAll().stream().map(wishItemMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WishItemDTO> findOne(Long id) {
        log.debug("Request to get WishItem : {}", id);
        return wishItemRepository.findById(id).map(wishItemMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WishItem : {}", id);
        wishItemRepository.deleteById(id);
    }
}
