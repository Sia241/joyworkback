package com.jee.joyworkbackend.controllers.api;


import com.jee.joyworkbackend.security.entities.AppUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IEmployeeApi {
    @GetMapping("/getUserInfos")
    AppUser getUserInfos(@RequestParam("username") String username) ;
}
