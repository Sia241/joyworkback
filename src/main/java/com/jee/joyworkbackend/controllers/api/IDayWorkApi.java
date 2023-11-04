package com.jee.joyworkbackend.controllers.api;

import com.jee.joyworkbackend.entities.DayWork;
import com.jee.joyworkbackend.shared.dtos.CreateDayWorkDto;
import com.jee.joyworkbackend.shared.dtos.EndDayWorkDto;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface IDayWorkApi {
    @GetMapping("/isWorkStarted")
    Boolean isWorkStarted(@RequestParam("todayDate") LocalDate todayDate, @RequestParam("username") String username);
    @PostMapping("/startWork")
    DayWork startWork(@RequestBody CreateDayWorkDto dayWork) ;
    @GetMapping("/isWorkEnded")
    Boolean isWorkEnded(@RequestParam("todayDate") LocalDate todayDate, @RequestParam("username") String username);
    @PostMapping("/endWork")
    DayWork endWork(@RequestBody EndDayWorkDto dayWork) ;
    @GetMapping("/getEmployeesDayWork")
    List<DayWork> getEmployeesDayWork(@RequestParam("todayDate") LocalDate todayDate) ;
}
