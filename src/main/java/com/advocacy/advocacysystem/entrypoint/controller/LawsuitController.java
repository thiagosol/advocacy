package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import com.advocacy.advocacysystem.core.usecase.base.CreateBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.base.GetAllBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.base.GetBaseByIdUseCase;
import com.advocacy.advocacysystem.core.usecase.base.UpdateBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.lawsuit.*;

import com.advocacy.advocacysystem.entrypoint.dto.lawsuit.LawsuitCreateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.lawsuit.LawsuitUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController("LawsuitController")
@RequestMapping("/v1/lawsuits")
@Api("Processos")
public class LawsuitController {

    private final CreateBaseUseCase<Lawsuit> createLawsuitUseCase;
    private final GetAllBaseUseCase<Lawsuit> getAllLawsuitUseCase;
    private final GetBaseByIdUseCase<Lawsuit> getLawsuitByIdUseCase;
    private final UpdateBaseUseCase<Lawsuit> updateLawsuitUseCase;
    private final AddCustomersToLawsuitUseCase addCustomersToLawsuitUseCase;
    private final RemoveCustomersToLawsuitUseCase removeCustomersToLawsuitUseCase;
    private final GetLawsuitByCustomerUseCase getLawsuitByCustomerUseCase;

    @ApiOperation(value = "Retorna todos os processos", response = Page.class)
    @GetMapping
    public ResponseEntity<Page<Lawsuit>> getAllLawsuits(@PageableDefault(direction = Sort.Direction.DESC, sort = "createdAt") Pageable page){
        return ResponseEntity.status(HttpStatus.CREATED).body(getAllLawsuitUseCase.execute(page));
    }

    @ApiOperation(value = "Retorna o processo por id", response = Lawsuit.class)
    @GetMapping("/{id}")
    public ResponseEntity<Lawsuit> getLawsuitById(@PathVariable("id") Long lawsuitId){
        return ResponseEntity.status(HttpStatus.CREATED).body(getLawsuitByIdUseCase.execute(lawsuitId));
    }

    @ApiOperation(value = "Retorna todos os processos do cliente", response = Page.class)
    @GetMapping("/customer/{id}")
    public ResponseEntity<Page<Lawsuit>> getAllLawsuitsByCustomer(@PathVariable("id") Long lawsuitId,
                                                                  @PageableDefault(direction = Sort.Direction.DESC, sort = "createdAt") Pageable page){
        return ResponseEntity.status(HttpStatus.CREATED).body(getLawsuitByCustomerUseCase.execute(lawsuitId, page));
    }

    @ApiOperation(value = "Cria novo processo", response = Lawsuit.class)
    @PostMapping
    public ResponseEntity<Lawsuit> createLawsuit(@RequestBody LawsuitCreateDTO lawsuitCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(createLawsuitUseCase.execute(lawsuitCreateDTO.toLawsuit()));
    }

    @ApiOperation(value = "Atualiza processo")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLawsuit(@PathVariable("id") Long lawsuitId,
                                               @RequestBody LawsuitUpdateDTO lawsuitUpdateDTO){
        updateLawsuitUseCase.execute(lawsuitUpdateDTO.toLawsuit(lawsuitId));
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Adiciona clientes ao processo")
    @PostMapping("/{id}/add-customers")
    public ResponseEntity<Void> addCustomers(@PathVariable("id") Long lawsuitId,
                                               @RequestBody Set<Long> customers){
        addCustomersToLawsuitUseCase.execute(lawsuitId, customers);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Remove clientes ao processo")
    @PostMapping("/{id}/remove-customers")
    public ResponseEntity<Void> removeCustomers(@PathVariable("id") Long lawsuitId,
                                             @RequestBody Set<Long> customers){
        removeCustomersToLawsuitUseCase.execute(lawsuitId, customers);
        return ResponseEntity.ok().build();
    }

}
