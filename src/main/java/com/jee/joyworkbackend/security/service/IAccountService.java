package com.jee.joyworkbackend.security.service;


import com.jee.joyworkbackend.security.entities.AppRole;
import com.jee.joyworkbackend.security.entities.AppUser;

public interface IAccountService {
    AppUser addUser(String nom, String prenom, String email, String password, String confirmPasswor);
    AppRole addNewRole(String role) ;
    void addRoleToUser(String email , String role);
    void removeRoleFromUser(String email , String role);
    AppUser loadUserByUsername(String email) ;

    AppUser getUserInfos(String username);
//
//    String getEmployees(Model model , int p , int s, String kw) ;
}
