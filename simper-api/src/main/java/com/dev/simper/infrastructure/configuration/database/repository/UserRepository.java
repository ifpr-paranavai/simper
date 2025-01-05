package com.dev.simper.infrastructure.configuration.database.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.simper.infrastructure.configuration.database.schema.UserSchema;

@Repository
public interface UserRepository extends JpaRepository<UserSchema, Long>{
    Optional<UserSchema> findByEmail(String email);
}
