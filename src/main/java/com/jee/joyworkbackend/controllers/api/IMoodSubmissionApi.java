package com.jee.joyworkbackend.controllers.api;

import com.jee.joyworkbackend.entities.MoodSubmission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

public interface IMoodSubmissionApi {
    @PostMapping("/saveMood")
    MoodSubmission saveMood(@RequestBody MoodSubmission moodSubmission) ;
    @GetMapping("/getMoodStatis/{todayDate}")
    List<Long> getMoodStatis(@PathVariable("todayDate") String string);
    @GetMapping("/verifyTokenExistants/{token}")
    Boolean verifyTokenExistants(@PathVariable("token") String token) ;
}
