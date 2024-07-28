package com.dev.simper.controller;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.simper.dto.user.UserDto;
import com.dev.simper.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userServiceImpl.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.ok(userServiceImpl.save(dto));
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.ok(userServiceImpl.update(dto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userServiceImpl.delete(id);
    }
}
