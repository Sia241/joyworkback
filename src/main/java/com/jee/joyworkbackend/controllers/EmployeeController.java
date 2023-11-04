package com.jee.joyworkbackend.controllers;


import com.jee.joyworkbackend.controllers.api.IEmployeeApi;
import com.jee.joyworkbackend.security.entities.AppUser;
import com.jee.joyworkbackend.security.service.IAccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@AllArgsConstructor
public class EmployeeController implements IEmployeeApi {
    private IAccountService iAccountService ;
    @Override
    public AppUser getUserInfos(String username) {
        return iAccountService.getUserInfos(username);
    }
}
