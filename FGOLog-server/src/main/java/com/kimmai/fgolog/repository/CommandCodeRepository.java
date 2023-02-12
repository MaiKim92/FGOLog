package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.CommandCode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Spring Data SQL repository for the CommandCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommandCodeRepository extends JpaRepository<CommandCode, Long> {}
