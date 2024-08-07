package com.dev.simper.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.simper.dto.EmployeeDto;
import com.dev.simper.service.EmployeeServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {
  private EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.ok(employeeServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeServiceImpl.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> save(@Valid @RequestBody EmployeeDto dto) {
        return ResponseEntity.ok(employeeServiceImpl.save(dto));
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> update(@Valid @RequestBody EmployeeDto dto) {
        return ResponseEntity.ok(employeeServiceImpl.update(dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
      employeeServiceImpl.delete(id);
    }
}
