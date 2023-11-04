package com.jee.joyworkbackend.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
public class PauseCreationDto {
    private LocalDate date;
    private LocalTime startTime;
    private String user;
}
