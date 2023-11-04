package com.jee.joyworkbackend.repositories;


import com.jee.joyworkbackend.entities.DayWork;
import com.jee.joyworkbackend.entities.MoodSubmission;
import com.jee.joyworkbackend.entities.MoodToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MoodTokenRepository extends JpaRepository<MoodToken, Long> {
//    @Query("SELECT  e FROM MoodToken e WHERE e.token =:token")
    MoodToken findMoodTokenByToken(String token);
}