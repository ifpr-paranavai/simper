package com.dev.simper.infrastructure.configuration.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.simper.infrastructure.configuration.database.schema.InstitutionSchema;

@Repository
public interface InstitutionRepository extends JpaRepository<InstitutionSchema, Long> { }
