package com.jee.joyworkbackend.repositories;


import com.jee.joyworkbackend.entities.Pause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPauseRepository extends JpaRepository<Pause, Long> {
    @Query("SELECT SUM(p.duration) FROM Pause p Where p.id=:id")
    Double getSumDurationsByWorkDayId(@Param("id") Long id);
}
