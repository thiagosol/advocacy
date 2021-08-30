package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import com.advocacy.advocacysystem.core.usecase.lawsuit.CreateLawsuitUseCase;

import com.advocacy.advocacysystem.entrypoint.dto.lawsuit.LawsuitCreateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("LawsuitController")
@RequestMapping("/v1/lawsuits")
@Api("Processos")
public class LawsuitController {

    private final CreateLawsuitUseCase createLawsuitUseCase;

    @ApiOperation(value = "Cria novo processo", response = Lawsuit.class)
    @PostMapping
    public ResponseEntity<Lawsuit> createLawsuit(@RequestBody LawsuitCreateDTO lawsuitCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(createLawsuitUseCase.execute(lawsuitCreateDTO.toLawsuit()));
    }
    
}
