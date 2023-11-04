package com.jee.joyworkbackend.controllers;

import com.jee.joyworkbackend.controllers.api.IDayWorkApi;
import com.jee.joyworkbackend.entities.DayWork;
import com.jee.joyworkbackend.services.IDayWorkService;
import com.jee.joyworkbackend.shared.dtos.CreateDayWorkDto;
import com.jee.joyworkbackend.shared.dtos.EndDayWorkDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@Data
@AllArgsConstructor
public class DayWorkController implements IDayWorkApi {
    private IDayWorkService iDayWorkService;
    @Override
    public Boolean isWorkStarted(LocalDate todayDate, String username) {
        return iDayWorkService.isWorkStarted(todayDate , username);
    }

    @Override
    public DayWork startWork(CreateDayWorkDto dayWork) {
        return iDayWorkService.startWork(dayWork);
    }

    @Override
    public Boolean isWorkEnded(LocalDate todayDate, String username) {
        return iDayWorkService.isWorkEnded(todayDate ,username);
    }

    @Override
    public DayWork endWork(EndDayWorkDto dayWork) {
        return iDayWorkService.endWork(dayWork);
    }

    @Override
    public List<DayWork> getEmployeesDayWork(LocalDate todayDate) {
        return iDayWorkService.getEmployeesDayWork(todayDate);
    }
//    private IDayWorkService iDayWorkService ;
//    private IAccountService iAccountService ;
//
//    @GetMapping("/")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String home()
//        {
//            return "redirect:/index" ;
//        }
//
//    @GetMapping("/index")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String index(Model model ,@RequestParam(name = "notYet",defaultValue = "true" ) Boolean notYet, @RequestParam(name = "start",defaultValue = "false" ) Boolean start , @RequestParam(name = "end" , defaultValue = "false") Boolean end
//    , @RequestParam(name = "pause" , defaultValue = "false") Boolean pause) {
//        model.addAttribute("start", start); // Set to true or false based on your logic
//        model.addAttribute("end", end); // Set to true or false based on your logic
//        model.addAttribute("notYet", notYet); // Set to true or false based on your logic
//        model.addAttribute("pause",pause); // Set the message based on your logic
//        return "index";
//    }
//    public String saveDayWork(DayWork dayWork , BindingResult bindingResult) {
//        return iDayWorkService.saveDayWork(dayWork,bindingResult);
//    }
//    @PostMapping("/workday/start")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String startWorkday(Model model) {
//        return iDayWorkService.startWorkDay(model);
//    }
//    @PostMapping("/workday/end")
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public String endWorkday(Model model) {
//        return iDayWorkService.endWorkDay(model);
//    }
}
