package com.jee.joyworkbackend.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class PauseEndingDto {
    private Long idPause;
    private LocalTime endTime ;
}
