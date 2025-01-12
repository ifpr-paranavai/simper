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
import com.dev.simper.usecase.institution.contract.IDeleteInstitutionUseCase;
import com.dev.simper.usecase.institution.contract.IGetInstitutionUseCase;
import com.dev.simper.usecase.institution.contract.IListInstitutionUseCase;
import com.dev.simper.usecase.institution.contract.ISaveInstitutionUseCase;
import com.dev.simper.usecase.institution.contract.IUpdateInstitutionUseCase;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/v1/institutions")
public class InstitutionController {

    private final ISaveInstitutionUseCase iSaveInstitutionUseCase;
    private final IUpdateInstitutionUseCase iUpdateInstitutionUseCase;
    private final IDeleteInstitutionUseCase iDeleteInstitutionUseCase;
    private final IGetInstitutionUseCase iGetInstitutionUseCase; 
    private final IListInstitutionUseCase iListInstitutionUseCase;

    public InstitutionController(
        ISaveInstitutionUseCase iSaveInstitutionUseCase,
        IUpdateInstitutionUseCase iUpdateInstitutionUseCase,
        IDeleteInstitutionUseCase iDeleteInstitutionUseCase,
        IGetInstitutionUseCase iGetInstitutionUseCase,
        IListInstitutionUseCase iListInstitutionUseCase
    ) {
        this.iSaveInstitutionUseCase = iSaveInstitutionUseCase;
        this.iUpdateInstitutionUseCase = iUpdateInstitutionUseCase;
        this.iDeleteInstitutionUseCase = iDeleteInstitutionUseCase;
        this.iGetInstitutionUseCase = iGetInstitutionUseCase;
        this.iListInstitutionUseCase = iListInstitutionUseCase;
    }

    @PostMapping
    ResponseEntity<InstitutionModel> save(@Valid @RequestBody InstitutionDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iSaveInstitutionUseCase.execute(dto));
    }

    @GetMapping("/{id}")
    ResponseEntity<InstitutionModel> get(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(iGetInstitutionUseCase.execute(id));
    }

    @GetMapping
    ResponseEntity<List<InstitutionModel>> list() {
        return ResponseEntity.ok(iListInstitutionUseCase.execute());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable @Positive Long id) {
        iDeleteInstitutionUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<InstitutionModel> update(@PathVariable @Positive Long id, @Valid @RequestBody InstitutionDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(iUpdateInstitutionUseCase.execute(dto));
    }
}
