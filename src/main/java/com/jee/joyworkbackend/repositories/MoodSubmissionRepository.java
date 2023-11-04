package com.jee.joyworkbackend.repositories;


import com.jee.joyworkbackend.entities.MoodSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface MoodSubmissionRepository extends JpaRepository<MoodSubmission, Long> {
    @Query("SELECT COUNT(m) FROM MoodSubmission m Where m.submissionDate=:todayDate AND m.mood like :mood")
    Long countHappyMoods(@Param("todayDate") LocalDate todayDate, @Param("mood") String mood);
}
