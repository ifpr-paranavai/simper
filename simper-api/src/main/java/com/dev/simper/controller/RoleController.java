package com.dev.simper.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.simper.dto.user.RoleDto;
import com.dev.simper.service.RoleServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/roles")
public class RoleController {

    private RoleServiceImpl roleServiceImpl;

    public RoleController(RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> findAll() {
        return ResponseEntity.ok(roleServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roleServiceImpl.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoleDto> save(@Valid @RequestBody RoleDto dto) {
        return ResponseEntity.ok(roleServiceImpl.save(dto));
    }

    @PutMapping
    public ResponseEntity<RoleDto> update(@Valid @RequestBody RoleDto dto) {
        return ResponseEntity.ok(roleServiceImpl.update(dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roleServiceImpl.delete(id);
    }
}
