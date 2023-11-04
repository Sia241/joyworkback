package com.jee.joyworkbackend.security.service;


import com.jee.joyworkbackend.security.entities.AppRole;
import com.jee.joyworkbackend.security.entities.AppUser;
import com.jee.joyworkbackend.security.repo.IAppRoleRepository;
import com.jee.joyworkbackend.security.repo.IAppUserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional
@Data
@AllArgsConstructor
@Builder
public class AccountServiceImp implements IAccountService {
    private IAppUserRepository iAppUserRepository;
    private IAppRoleRepository iAppRoleRepository;
    private PasswordEncoder passwordEncoder ;
    @Override
    public AppUser addUser(String nom , String prenom, String email , String password , String confirmPassword) {
        AppUser user = iAppUserRepository.findAppUserByEmail(email) ;
        if(user != null){
            throw  new RuntimeException("This User already Exist!");
        }
        if(!password.equals(confirmPassword)) {
            throw  new RuntimeException("Password Not match!");
        }
         user = AppUser.builder()
                .id(null)
                .nom(nom)
                .prenom(prenom)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();
        AppUser saveUser = iAppUserRepository.save(user);
        return saveUser ;
    }
    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = iAppRoleRepository.findById(role).orElse(null);
        if(appRole !=null) {
            throw new RuntimeException("This role Already Exist") ;
        }
        appRole = AppRole.builder()
                .role(role)
                .build();
        return iAppRoleRepository.save(
                appRole
        );
    }
    @Override
    public void addRoleToUser(String email, String role) {
        AppUser appUser = iAppUserRepository.findAppUserByEmail(email);
        AppRole appRole = iAppRoleRepository.findById(role).get();
        appUser.getRoles().add(appRole);
    }
    @Override
    public void removeRoleFromUser(String email, String role) {
        AppUser appUser = iAppUserRepository.findAppUserByEmail(email);
        AppRole appRole = iAppRoleRepository.findById(role).get();
        appUser.getRoles().remove(appRole);
    }
//
    @Override
    public AppUser loadUserByUsername(String email) {
        return iAppUserRepository.findAppUserByEmail(email);
    }

    @Override
    public AppUser getUserInfos(String username) {
        AppUser user = iAppUserRepository.findAppUserByEmail(username) ;
        if(user !=null) {
            return user ;
        }
        return null;
    }
//
//    @Override
//    public String getEmployees(Model model, int p, int s, String kw) {
//        return null;
//    }

//    @Override
//    public String getEmployees(Model model , int p , int s,String kw) {
//    Page<UserDetailsServiceImp> pageEmployees = iAppUserRepository.findAll(kw , PageRequest.of(p , s));
//    model.addAttribute("listEmployees" , pageEmployees.getContent()) ;
//    model.addAttribute("pages" , new int[pageEmployees.getTotalPages()]) ;
//    model.addAttribute("currentPage" ,p) ;
//    model.addAttribute("keyword" , kw) ;
//    return "employees" ;
}
//}
