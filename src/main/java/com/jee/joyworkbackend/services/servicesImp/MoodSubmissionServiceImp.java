package com.jee.joyworkbackend.services.servicesImp;


import com.jee.joyworkbackend.entities.MoodSubmission;
import com.jee.joyworkbackend.entities.MoodToken;
import com.jee.joyworkbackend.repositories.MoodSubmissionRepository;
import com.jee.joyworkbackend.repositories.MoodTokenRepository;
import com.jee.joyworkbackend.services.IMoodSubmissionService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@Data
@AllArgsConstructor
public class MoodSubmissionServiceImp implements IMoodSubmissionService {
    private MoodSubmissionRepository moodSubmissionRepository ;
    private MoodTokenRepository moodTokenRepository ;
    @Override
    public MoodSubmission save(MoodSubmission moodSubmission) {
        if(moodSubmission != null) {
            return moodSubmissionRepository.save(moodSubmission) ;
        }
        throw new RuntimeException("Mood is null") ;
    }

    @Override
    public List<Long> getMoodStatis(LocalDate todayDate) {
        List<Long> moods = new ArrayList<>();
        Long happyMoods = this.moodSubmissionRepository.countHappyMoods(todayDate, "happy") ;
        Long sadMoods = this.moodSubmissionRepository.countHappyMoods(todayDate, "sad") ;
        Long neutralMoods = this.moodSubmissionRepository.countHappyMoods(todayDate, "neutral") ;
        Long angryMoods = this.moodSubmissionRepository.countHappyMoods(todayDate, "angry") ;
        Long badMoods = this.moodSubmissionRepository.countHappyMoods(todayDate, "bad") ;
        moods.add(happyMoods) ;
        moods.add(sadMoods) ;
        moods.add(neutralMoods) ;
        moods.add(angryMoods) ;
        moods.add(badMoods) ;
        for (Long mood : moods) {
            System.out.println("mood number : "+mood);
        }
        return moods;
    }

    @Override
    public Boolean verifyTokenExistants(String token) {
        MoodToken token1 = moodTokenRepository.findMoodTokenByToken(token);
        if(token1 !=null) {
            log.info("Token Already Exist!!!");
            return true ;
        }
        else {
            log.info("Token not exist, it will save!");
            MoodToken token2 = new MoodToken() ;
            token2.setToken(token);
            moodTokenRepository.save(token2);
            return false;
        }
    }
}
