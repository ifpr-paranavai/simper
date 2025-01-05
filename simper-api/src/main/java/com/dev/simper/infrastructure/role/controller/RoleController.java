package com.dev.simper.infrastructure.role.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.infrastructure.role.dto.RoleDto;
import com.dev.simper.usecase.role.IRoleUseCase;

import java.util.List;

@RestController
@RequestMapping("/v1/roles")
public class RoleController {

    private final IRoleUseCase iRoleUseCase;

    public RoleController(IRoleUseCase iRoleUseCase) {
        this.iRoleUseCase = iRoleUseCase;
    }

    @GetMapping
    public ResponseEntity<List<RoleModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(iRoleUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleModel> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iRoleUseCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<RoleModel> save(@Valid @RequestBody RoleDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iRoleUseCase.save(dto));
    }

    @PutMapping
    public ResponseEntity<RoleModel> update(@Valid @RequestBody RoleDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(iRoleUseCase.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iRoleUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
