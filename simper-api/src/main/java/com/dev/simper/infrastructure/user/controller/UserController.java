package com.dev.simper.infrastructure.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.infrastructure.user.dto.UserDto;
import com.dev.simper.usecase.user.contract.IDeleteUserUseCase;
import com.dev.simper.usecase.user.contract.IGetUserUseCase;
import com.dev.simper.usecase.user.contract.IListUserUseCase;
import com.dev.simper.usecase.user.contract.ISaveUserUseCase;
import com.dev.simper.usecase.user.contract.IUpdatedUserUseCase;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final ISaveUserUseCase iSaveUserUseCase;
    private final IUpdatedUserUseCase iUpdatedUserUseCase;
    private final IDeleteUserUseCase iDeleteUserUseCase;
    private final IGetUserUseCase iGetUserUseCase;
    private final IListUserUseCase iListUserUseCase;

    UserController(
        ISaveUserUseCase iSaveUserUseCase,
        IUpdatedUserUseCase iUpdatedUserUseCase,
        IDeleteUserUseCase iDeleteUserUseCase,
        IGetUserUseCase iGetUserUseCase,
        IListUserUseCase iListUserUseCase
    ) {
        this.iSaveUserUseCase = iSaveUserUseCase;
        this.iUpdatedUserUseCase = iUpdatedUserUseCase;
        this.iDeleteUserUseCase = iDeleteUserUseCase;
        this.iGetUserUseCase = iGetUserUseCase;
        this.iListUserUseCase = iListUserUseCase;
    }

    @GetMapping
    ResponseEntity<List<UserModel>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(iListUserUseCase.execute());
    }

    @GetMapping("/{id}")
    ResponseEntity<UserModel> get(@PathVariable @Positive Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iGetUserUseCase.execute(id));
    }

    @PostMapping
    ResponseEntity<UserModel> save(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iSaveUserUseCase.execute(dto));
    }

    @PutMapping("/{id}")
    ResponseEntity<UserModel> update(@PathVariable @Positive Long id, @Valid @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(iUpdatedUserUseCase.execute(dto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        iDeleteUserUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
