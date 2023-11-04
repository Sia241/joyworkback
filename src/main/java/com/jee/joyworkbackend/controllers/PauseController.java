package com.jee.joyworkbackend.controllers;

import com.jee.joyworkbackend.controllers.api.IPauseApi;
import com.jee.joyworkbackend.entities.Pause;
import com.jee.joyworkbackend.services.IPauseService;
import com.jee.joyworkbackend.shared.dtos.PauseCreationDto;
import com.jee.joyworkbackend.shared.dtos.PauseEndingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@AllArgsConstructor
//@CrossOrigin("*")
public class PauseController implements IPauseApi {
    private IPauseService iPauseService ;
    @Override
    public Pause pause(PauseCreationDto pause) {
        return iPauseService.pause(pause);
    }

    @Override
    public Pause endPause(PauseEndingDto pauseEnding) {
        return iPauseService.endPause(pauseEnding);
    }
//    private IPauseService iPauseService ;

//    @Override
//    public void pause(Pause pause) {
//        iPauseService.savePause(pause);
//    }
}
