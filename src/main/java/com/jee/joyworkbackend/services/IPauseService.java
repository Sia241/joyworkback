package com.jee.joyworkbackend.services;


import com.jee.joyworkbackend.entities.Pause;
import com.jee.joyworkbackend.shared.dtos.PauseCreationDto;
import com.jee.joyworkbackend.shared.dtos.PauseEndingDto;

public interface IPauseService {
    Pause pause(PauseCreationDto pause);

    Pause endPause(PauseEndingDto pauseEnding);
}
