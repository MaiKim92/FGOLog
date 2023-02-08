package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.WishItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WishItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WishItemRepository extends JpaRepository<WishItem, Long> {}
