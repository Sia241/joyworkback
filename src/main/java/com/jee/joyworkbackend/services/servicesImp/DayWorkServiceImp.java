package com.jee.joyworkbackend.services.servicesImp;


import com.jee.joyworkbackend.entities.DayWork;
import com.jee.joyworkbackend.entities.Pause;
import com.jee.joyworkbackend.repositories.IDayWorkRepository;
import com.jee.joyworkbackend.repositories.IPauseRepository;
import com.jee.joyworkbackend.security.entities.AppUser;
import com.jee.joyworkbackend.security.repo.IAppUserRepository;
import com.jee.joyworkbackend.security.service.IAccountService;
import com.jee.joyworkbackend.services.IDayWorkService;
import com.jee.joyworkbackend.shared.dtos.CreateDayWorkDto;
import com.jee.joyworkbackend.shared.dtos.EndDayWorkDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@Transactional
@Data
@AllArgsConstructor
public class DayWorkServiceImp implements IDayWorkService {
    private IDayWorkRepository iDayWorkRepository ;
    private IAppUserRepository iAppUserRepository;
    private IPauseRepository iPauseRepository ;
    private IAccountService iAccountService ;

    @Override
    public Boolean isWorkStarted(LocalDate todayDate, String username) {
        DayWork existingDayWork = iDayWorkRepository.findDayWorkByUserNameAndDate(todayDate, username);
        if(existingDayWork == null) {
            return false ;
        }
        return true;
    }
    @Override
    public DayWork startWork(CreateDayWorkDto createDayWorkDto) {
        AppUser appUser = iAppUserRepository.findAppUserByEmail(createDayWorkDto.getUser());
        if(appUser != null) {
            DayWork dayWork = new DayWork();
            dayWork.setAppUser(appUser);
            dayWork.setDate(createDayWorkDto.getDate());
            dayWork.setStartTime(createDayWorkDto.getStartTime());
            return iDayWorkRepository.save(dayWork) ;
        }
        throw new RuntimeException("User is Null!");
    }

    @Override
    public Boolean isWorkEnded(LocalDate todayDate, String username) {
        DayWork existingDayWork = iDayWorkRepository.findDayWorkByUserNameAndDate(todayDate, username);
        if(existingDayWork != null) {
            if (existingDayWork.getEndTime() != null) {
                return true ;
            }
            return false;
        }
        return false ;
    }

    @Override
    public DayWork endWork(EndDayWorkDto dayWork) {
        DayWork existingDayWork = iDayWorkRepository.findDayWorkByUserNameAndDate(dayWork.getDate(), dayWork.getUser());
        long sumPausesSeconds = 0 ;
        if (existingDayWork != null) {
            Double sumPausesMinutes = iPauseRepository.getSumDurationsByWorkDayId(existingDayWork.getId());
            if(sumPausesMinutes!=null) {
                sumPausesSeconds = (long) (sumPausesMinutes * 60); // Convert minutes to seconds
            }
            Duration workDuration = Duration.between(existingDayWork.getStartTime(), dayWork.getEndTime());
            // Calculate the working duration minus pauses
            long workDurationInSeconds = workDuration.getSeconds() - sumPausesSeconds;
            if (workDurationInSeconds < 0) {
                workDurationInSeconds = 0; // Ensure the working duration is not negative
            }
            // Calculate hours, minutes, and seconds for working duration
            long hours = workDurationInSeconds / 3600;
            long remainingSeconds = workDurationInSeconds % 3600;
            long minutes = remainingSeconds / 60;
            long seconds = remainingSeconds % 60;
            // Calculate overtime hours in hours, minutes, and seconds
            long regularWorkdaySeconds = 8 * 3600; // Assuming a regular workday is 8 hours
            long overtimeDurationSeconds = workDurationInSeconds - regularWorkdaySeconds;
            long overtimeHours = overtimeDurationSeconds / 3600;
            long overtimeRemainingSeconds = overtimeDurationSeconds % 3600;
            long overtimeMinutes = overtimeRemainingSeconds / 60;
            long overtimeSeconds = overtimeRemainingSeconds % 60;
            // Format working hours
            String hoursWorkFormat = hours + " Hours : " + minutes + " Minutes : " + seconds + " Seconds";
            // Format overtime hours
            String overtimeHoursFormat = overtimeHours + " Hours : " + overtimeMinutes + " Minutes : " + overtimeSeconds + " Seconds";
            existingDayWork.setEndTime(dayWork.getEndTime());
            existingDayWork.setTotalHoursWorked((double) hours + (double) minutes / 60.0 + (double) seconds / 3600.0);
            existingDayWork.setOvertimeHours((double) overtimeHours);
            existingDayWork.setTotalWorkedFormat(hoursWorkFormat);
            existingDayWork.setTotalOvertimeWorkedHoursFormat(overtimeHoursFormat);
            return iDayWorkRepository.save(existingDayWork);
        } else {
            log.warn("There is no DayWork with those info!");
            return null;
        }
    }

    @Override
    public List<DayWork> getEmployeesDayWork(LocalDate todayDate) {
        List<DayWork>  dayWorks = iDayWorkRepository.findDayWorksByDate(todayDate) ;
        for(DayWork dayWork : dayWorks) {
            System.out.println(dayWork.getAppUser().getPrenom());
        }
        return dayWorks;
    }



}
