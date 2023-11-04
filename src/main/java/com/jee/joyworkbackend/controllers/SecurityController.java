package com.jee.joyworkbackend.controllers;

import com.jee.joyworkbackend.security.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {

    private AuthenticationManager authenticationManager ;
    private JwtEncoder jwtEncoder ;
    private IAccountService accountService ;
    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication ;
    }
    @PostMapping("/login")
    public Map<String , String> login(String username , String password) {
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username, password
                )
        );
        Instant instant = Instant.now();
        String scope = authentication.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" ")) ;
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .subject(username)
                .claim("scope" , scope)
                .build() ;
        JwtEncoderParameters jwtEncoderParameters =
                JwtEncoderParameters.from(
                        JwsHeader.with(MacAlgorithm.HS512).build(),
                        jwtClaimsSet
                );
        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue() ;
        return Map.of("access-token", jwt) ;
    }
//    @PostMapping("/addNewUser")
//    @PreAuthorize("hasAuthority('SCOPE_HeadAdmin')")
//    public AppUser addUser(@RequestParam("username") String username , @RequestParam("password") String password) {
//        return accountService.addUser(username , password) ;
//    }
    @PostMapping("/addRoleToUser")
    @PreAuthorize("hasAuthority('SCOPE_Admin')")
    public void addRoleToUser(@RequestParam("username") String username,  @RequestParam("role") String role) {
        accountService.addRoleToUser(username , role);
    }
//    @PostMapping("/updateUserInformation")
//    public void updateUserInformation(
//            @RequestParam(name = "username") String username,
//            @RequestParam(name = "oldPassword") String oldPassword,
//            @RequestParam(name = "newPassword" , required = false) String newPassword,
//            @RequestParam(name= "newUsername" , required = false) String newUsername
//    )
//    {
//        accountService.updateUserInformation(username , oldPassword ,newPassword ,newUsername);
//    }
//    @GetMapping("/getUsers/{role}")
//    @PreAuthorize("hasAuthority('SCOPE_Admin')")
//    public List<AppUser> getUsersByRole(@PathVariable("role") String role) {
//        return accountService.getEmployees(role) ;
//    }
//    @DeleteMapping("users/delete/{id}")
//    @PreAuthorize("hasAuthority('SCOPE_HeadAdmin')")
//    public void delete(@PathVariable("id") Long id) {
//        accountService.deleteUser(id) ;
//    };
//    @PostMapping("/users")
//    @PreAuthorize("hasAuthority('SCOPE_HeadAdmin')")
//    public AppUserDto addNewUser(@RequestBody AppUserDto user) {
//        return accountService.addNewUser(user.getUsername() , user.getPassword()) ;
//    };
}
