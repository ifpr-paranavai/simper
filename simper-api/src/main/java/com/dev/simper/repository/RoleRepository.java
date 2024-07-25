package com.dev.simper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.simper.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
