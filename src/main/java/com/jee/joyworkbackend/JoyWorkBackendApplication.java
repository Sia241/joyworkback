package com.jee.joyworkbackend;

import com.jee.joyworkbackend.security.service.IAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JoyWorkBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JoyWorkBackendApplication.class, args);
    }
//    @Bean
    CommandLineRunner commandLineRunnerUserDetails(IAccountService iAccountService){
        return args -> {
//            iAccountService.addNewRole("USER");
//            iAccountService.addNewRole("ADMIN");
//            iAccountService.addUser("Mohamed" , "Folan", "mohamed.folan@gmail.com", "user1234", "user1234");
//            iAccountService.addUser("Farid" , "Hamid", "farid.hamad@gmail.com", "user1234", "user1234");
            iAccountService.addUser("MAHIR" , "Rochdi", "mahir.rochdi@gmail.com", "user1234", "user1234");
//            iAccountService.addUser("admin" , "admin", "admin@gmail.com", "user1234", "user1234");
//            iAccountService.addRoleToUser("mohamed.folan@gmail.com", "USER");
//            iAccountService.addRoleToUser("farid.hamad@gmail.com", "USER");
            iAccountService.addRoleToUser("mahir.rochdi@gmail.com", "USER");
//            iAccountService.addRoleToUser("admin@gmail.com", "ADMIN");
        };
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
