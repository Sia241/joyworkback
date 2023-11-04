package com.jee.joyworkbackend.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data @AllArgsConstructor
public class EndDayWorkDto {
    private LocalDate date;
    private LocalTime endTime;
    private String user;
}
// Getters and setters
