package com.jee.joyworkbackend.security.repo;


import com.jee.joyworkbackend.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByEmail(String email) ;
}
