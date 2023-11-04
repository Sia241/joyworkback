package com.jee.joyworkbackend.services;


import com.jee.joyworkbackend.entities.DayWork;
import com.jee.joyworkbackend.shared.dtos.CreateDayWorkDto;
import com.jee.joyworkbackend.shared.dtos.EndDayWorkDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IDayWorkService {

    Boolean isWorkStarted(LocalDate todayDate, String username);

    DayWork startWork(CreateDayWorkDto createDayWorkDto);
    Boolean isWorkEnded(LocalDate todayDate, String username);
    DayWork endWork(EndDayWorkDto dayWork);
    List<DayWork> getEmployeesDayWork(LocalDate todayDate);
}
