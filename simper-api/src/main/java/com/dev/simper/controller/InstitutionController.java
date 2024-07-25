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

import com.dev.simper.dto.InstitutionDto;
import com.dev.simper.service.InstitutionService;

@RestController
@RequestMapping("/api/v1/institutions")
public class InstitutionController {

    public final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @PostMapping
    ResponseEntity<InstitutionDto> save(@RequestBody InstitutionDto dto) {
        return ResponseEntity.ok(institutionService.save(dto));
    }

    @PostMapping("/save-all")
    ResponseEntity<List<InstitutionDto>> saveAll(@RequestBody List<InstitutionDto> dtos) {
        return ResponseEntity.ok(institutionService.saveAll(dtos));
    }

    @GetMapping("/{id}")
    ResponseEntity<InstitutionDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(institutionService.findById(id));
    }

    @GetMapping
    ResponseEntity<List<InstitutionDto>> findAll() {
        return ResponseEntity.ok(institutionService.findAll());
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        institutionService.delete(id);
    }

    @PutMapping
    ResponseEntity<InstitutionDto> update(@RequestBody InstitutionDto dto) {
        return ResponseEntity.ok(institutionService.update(dto));
    }
}
