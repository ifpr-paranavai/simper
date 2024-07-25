package com.dev.simper.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.simper.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
