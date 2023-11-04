package com.jee.joyworkbackend.security.service;

import com.jee.joyworkbackend.security.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private IAccountService iAccountService ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = iAccountService.loadUserByUsername(username);
        if(appUser == null) {
            throw new UsernameNotFoundException(String.format("User %s Not Found!", username));
        }
        String[] roles = appUser.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new);
        UserDetails userDetails = User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(roles).build() ;
        return userDetails;
    }
}
