package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.Servant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Servant entity.
 */
@Repository
public interface ServantRepository extends JpaRepository<Servant, Long> {
    @Query(
        value = "select distinct servant from Servant servant left join fetch servant.parties",
        countQuery = "select count(distinct servant) from Servant servant"
    )
    Page<Servant> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct servant from Servant servant left join fetch servant.parties")
    List<Servant> findAllWithEagerRelationships();

    @Query("select distinct servant from Servant servant left join fetch servant.parties where servant.isHas = :isHas")
    List<Servant> findAllByIsHasWithEagerRelationships(@Param("isHas") Boolean isHas);

    @Query("select servant from Servant servant left join fetch servant.parties where servant.id =:id")
    Optional<Servant> findOneWithEagerRelationships(@Param("id") Long id);
}
