package com.jee.joyworkbackend.repositories;


import com.jee.joyworkbackend.entities.DayWork;
import com.jee.joyworkbackend.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IDayWorkRepository extends JpaRepository<DayWork, Long> {
    DayWork findDayWorkByAppUserAndDate(AppUser appUser, LocalDate currentDate);

    DayWork findDayWorkByAppUserIdAndDate(Long userID, LocalDate now);

    @Query("SELECT d FROM DayWork d Where d.appUser.email=:username AND d.date =:todayDate")
    DayWork findDayWorkByUserNameAndDate(@Param("todayDate") LocalDate todayDate, @Param("username") String username);
    List<DayWork> findDayWorksByDate(LocalDate todayDate);
    @Query("SELECT  e FROM DayWork e WHERE e.date =:date")
    List<DayWork> lReport(@Param("date") LocalDate todayDate);
}
