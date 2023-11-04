package com.jee.joyworkbackend.security.repo;


import com.jee.joyworkbackend.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppRoleRepository extends JpaRepository<AppRole,String> {
}
