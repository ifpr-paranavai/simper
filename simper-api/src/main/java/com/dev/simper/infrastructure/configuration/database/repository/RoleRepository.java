package com.dev.simper.infrastructure.configuration.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.simper.infrastructure.configuration.database.schema.RoleSchema;

@Repository
public interface RoleRepository extends JpaRepository<RoleSchema, Long>{ }
