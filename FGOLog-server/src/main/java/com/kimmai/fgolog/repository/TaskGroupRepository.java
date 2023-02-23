package com.kimmai.fgolog.repository;

import com.kimmai.fgolog.domain.TaskGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TaskGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {}
