package com.dev.simper.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dev.simper.model.Employee;

@Repository
public class EmployeeRepository extends JpaRepository<Employee, Long>{
  Optional<Employee> findByName(String name);
}
