package com.dev.simper.infrastructure.institution.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.simper.entity.institution.model.InstitutionModel;
import com.dev.simper.infrastructure.institution.dto.InstitutionDto;
import com.dev.simper.usecase.institution.IInstitutionUseCase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/v1/institutions")
public class InstitutionController {

    public final IInstitutionUseCase iInstitutionUseCase;

    public InstitutionController(IInstitutionUseCase iInstitutionUseCase) {
        this.iInstitutionUseCase = iInstitutionUseCase;
    }

    @PostMapping
    ResponseEntity<InstitutionModel> save(@Valid @RequestBody InstitutionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iInstitutionUseCase.save(dto));
    }

    @GetMapping("/{id}")
    ResponseEntity<InstitutionModel> findById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(iInstitutionUseCase.findById(id));
    }

    @GetMapping
    ResponseEntity<List<InstitutionModel>> findAll() {
        return ResponseEntity.ok(iInstitutionUseCase.findAll());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        iInstitutionUseCase.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<InstitutionModel> update(@PathVariable @Positive Long id, @Valid @RequestBody InstitutionDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(iInstitutionUseCase.update(dto));
    }
}
