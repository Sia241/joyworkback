package com.jee.joyworkbackend.services.servicesImp;


import com.jee.joyworkbackend.entities.DayWork;
import com.jee.joyworkbackend.entities.Pause;
import com.jee.joyworkbackend.repositories.IDayWorkRepository;
import com.jee.joyworkbackend.repositories.IPauseRepository;
import com.jee.joyworkbackend.services.IPauseService;
import com.jee.joyworkbackend.shared.dtos.PauseCreationDto;
import com.jee.joyworkbackend.shared.dtos.PauseEndingDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
@Transactional
@Data
@AllArgsConstructor
public class PauseServiceImp implements IPauseService {
    private IPauseRepository iPauseRepository ;
    private IDayWorkRepository iDayWorkRepository ;
    @Override
    public Pause pause(PauseCreationDto pause) {
        System.out.println("date = "+pause.getDate()+" User = "+pause.getUser());
        DayWork dayWork = iDayWorkRepository.findDayWorkByUserNameAndDate(pause.getDate(),pause.getUser());
        if(dayWork != null) {
            Pause pause1 = new Pause();
            pause1.setStartTimePause(pause.getStartTime());
            pause1.setWorkDay(dayWork);
            return iPauseRepository.save(pause1) ;
        }
        throw new RuntimeException("Day Work is Null!");
    }

    @Override
    public Pause endPause(PauseEndingDto pauseEnding) {
        Pause pause = iPauseRepository.findById(pauseEnding.getIdPause()).orElse(null);
        if (pause != null) {
            pause.setEndTimePause(pauseEnding.getEndTime());
            // Calculate the duration
            if (pause.getStartTimePause() != null && pauseEnding.getEndTime() != null) {
                Duration duration = Duration.between(pause.getStartTimePause(), pauseEnding.getEndTime());
                long durationInSeconds = duration.getSeconds();
                double durationInMinutes = (double) durationInSeconds / 60.0;
                pause.setDuration(durationInMinutes);
            }
            return iPauseRepository.save(pause);
        }
        return null;
    }
}
