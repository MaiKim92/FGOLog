package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.Servant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data SQL repository for the Servant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServantRepository extends JpaRepository<Servant, Long> {

    @Query("select distinct servant from Servant servant where servant.npLevel > 0")
    List<Servant> findAllOwned();

}
