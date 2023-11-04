package com.jee.joyworkbackend.services;

import com.jee.joyworkbackend.entities.MoodSubmission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IMoodSubmissionService {
    MoodSubmission save(MoodSubmission moodSubmission);

    List<Long> getMoodStatis(LocalDate todayDate);

    Boolean verifyTokenExistants(String token);
}
