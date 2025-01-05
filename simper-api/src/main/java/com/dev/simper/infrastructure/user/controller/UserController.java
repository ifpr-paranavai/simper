package com.dev.simper.infrastructure.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.simper.entity.user.model.UserModel;
import com.dev.simper.infrastructure.user.dto.UserDto;
import com.dev.simper.usecase.user.IUserUseCase;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final IUserUseCase iUserUseCase;

    UserController(IUserUseCase iUserUseCase) {
        this.iUserUseCase = iUserUseCase;
    }

    @GetMapping
    ResponseEntity<List<UserModel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(iUserUseCase.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<UserModel> findById(@PathVariable @Positive Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iUserUseCase.findById(id));
    }

    @PostMapping
    ResponseEntity<UserModel> save(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iUserUseCase.save(dto));
    }

    @PutMapping("/{id}")
    ResponseEntity<UserModel> update(@PathVariable @Positive Long id, @Valid @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(iUserUseCase.update(dto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        iUserUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
