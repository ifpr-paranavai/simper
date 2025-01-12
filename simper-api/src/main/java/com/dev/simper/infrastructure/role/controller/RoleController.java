package com.dev.simper.infrastructure.role.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.simper.entity.role.model.RoleModel;
import com.dev.simper.infrastructure.role.dto.RoleDto;
import com.dev.simper.usecase.role.contract.IDeleteRoleUseCase;
import com.dev.simper.usecase.role.contract.IGetRoleUseCase;
import com.dev.simper.usecase.role.contract.IListRoleUseCase;
import com.dev.simper.usecase.role.contract.ISaveRoleUseCase;
import com.dev.simper.usecase.role.contract.IUpdateRoleUseCase;

import java.util.List;

@RestController
@RequestMapping("/v1/roles")
public class RoleController {

    private final ISaveRoleUseCase iSaveRoleUseCase;
    private final IUpdateRoleUseCase iUpdateRoleUseCase;
    private final IDeleteRoleUseCase iDeleteRoleUseCase;
    private final IGetRoleUseCase iGetRoleUseCase; 
    private final IListRoleUseCase iListRoleUseCase;

    public RoleController(
        ISaveRoleUseCase iSaveRoleUseCase,
        IUpdateRoleUseCase iUpdateRoleUseCase,
        IDeleteRoleUseCase iDeleteRoleUseCase,
        IGetRoleUseCase iGetRoleUseCase,
        IListRoleUseCase iListRoleUseCase
    ) {
        this.iSaveRoleUseCase = iSaveRoleUseCase;
        this.iUpdateRoleUseCase = iUpdateRoleUseCase;
        this.iDeleteRoleUseCase = iDeleteRoleUseCase;
        this.iGetRoleUseCase = iGetRoleUseCase;
        this.iListRoleUseCase = iListRoleUseCase;
    }

    @GetMapping
    public ResponseEntity<List<RoleModel>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(iListRoleUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleModel> get(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iGetRoleUseCase.execute(id));
    }

    @PostMapping
    public ResponseEntity<RoleModel> save(@Valid @RequestBody RoleDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iSaveRoleUseCase.execute(dto));
    }

    @PutMapping
    public ResponseEntity<RoleModel> update(@Valid @RequestBody RoleDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(iUpdateRoleUseCase.execute(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iDeleteRoleUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
