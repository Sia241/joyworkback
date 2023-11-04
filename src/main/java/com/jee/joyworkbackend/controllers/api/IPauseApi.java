package com.jee.joyworkbackend.controllers.api;

//import com.jee.joywork.entities.Pause;
import com.jee.joyworkbackend.entities.Pause;
import com.jee.joyworkbackend.shared.dtos.PauseCreationDto;
import com.jee.joyworkbackend.shared.dtos.PauseEndingDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPauseApi {
    @PostMapping("/startPause")
    Pause pause(@RequestBody PauseCreationDto pause) ;
    @PostMapping("/endPause")
    Pause endPause(@RequestBody PauseEndingDto pauseEnding) ;
}
