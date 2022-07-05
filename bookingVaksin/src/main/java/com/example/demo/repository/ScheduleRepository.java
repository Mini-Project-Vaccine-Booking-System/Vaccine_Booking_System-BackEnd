package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    @Query(value = "SELECT * FROM schedule WHERE schedule.id_schedule = ? ", nativeQuery = true)
    Optional<Schedule> searchById (Long id);
}
