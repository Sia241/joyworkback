package com.jee.joyworkbackend.controllers;


import com.jee.joyworkbackend.controllers.api.IMoodSubmissionApi;
import com.jee.joyworkbackend.entities.MoodSubmission;
import com.jee.joyworkbackend.services.IMoodSubmissionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Data
@AllArgsConstructor
public class MoodSubmissionController implements IMoodSubmissionApi {
    private IMoodSubmissionService iMoodSubmissionService ;
    @Override
    public MoodSubmission saveMood(MoodSubmission moodSubmission) {
        return iMoodSubmissionService.save(moodSubmission);
    }

    @Override
    public List<Long> getMoodStatis(String todayDate) {
        LocalDate localDate = LocalDate.parse(todayDate);
        return iMoodSubmissionService.getMoodStatis(localDate);
    }

    @Override
    public Boolean verifyTokenExistants(String token) {
        return iMoodSubmissionService.verifyTokenExistants(token);
    }
}
