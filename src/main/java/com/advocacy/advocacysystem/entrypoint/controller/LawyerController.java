package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.domain.Lawyer;
import com.advocacy.advocacysystem.core.usecase.base.CreateBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.base.GetAllBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.base.GetBaseByIdUseCase;
import com.advocacy.advocacysystem.core.usecase.base.UpdateBaseUseCase;
import com.advocacy.advocacysystem.entrypoint.dto.lawyer.LawyerCreateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.lawyer.LawyerUpdateDTO;
import com.advocacy.advocacysystem.entrypoint.manager.PasswordManager;
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

import java.util.Optional;

@RequiredArgsConstructor
@RestController("LawyerController")
@RequestMapping("/v1/lawyers")
@Api("Advogados")
public class LawyerController {

    private final PasswordManager passwordManager;

    private final CreateBaseUseCase<Lawyer> createLawyerUseCase;
    private final GetAllBaseUseCase<Lawyer> getAllLawyerUseCase;
    private final GetBaseByIdUseCase<Lawyer> getLawyerByIdUseCase;
    private final UpdateBaseUseCase<Lawyer> updateLawyerUseCase;

    @ApiOperation(value = "Retorna todos os advogados", response = Page.class)
    @GetMapping
    public ResponseEntity<Page<Lawyer>> getAllL(@PageableDefault(direction = Sort.Direction.DESC, sort = "createdAt") Pageable page){
        return ResponseEntity.status(HttpStatus.CREATED).body(getAllLawyerUseCase.execute(page));
    }

    @ApiOperation(value = "Retorna o advogado por id", response = Lawyer.class)
    @GetMapping("/{id}")
    public ResponseEntity<Lawyer> getById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(getLawyerByIdUseCase.execute(id));
    }

    @ApiOperation(value = "Cria novo advogado", response = Lawyer.class)
    @PostMapping
    public ResponseEntity<Lawyer> create(@RequestBody LawyerCreateDTO lawyerCreateDTO){
        lawyerCreateDTO.setPassword(passwordManager.encode(lawyerCreateDTO.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(createLawyerUseCase.execute(lawyerCreateDTO.toLawyer()));
    }

    @ApiOperation(value = "Atualiza advogado")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLawsuit(@PathVariable("id") Long id,
                                               @RequestBody LawyerUpdateDTO lawyerUpdateDTO){
        Optional.ofNullable(lawyerUpdateDTO.getPassword()).ifPresent((update) -> lawyerUpdateDTO.setPassword(passwordManager.encode(lawyerUpdateDTO.getPassword())));
        lawyerUpdateDTO.setPassword(passwordManager.encode(lawyerUpdateDTO.getPassword()));
        updateLawyerUseCase.execute(lawyerUpdateDTO.toLawyer(id));
        return ResponseEntity.ok().build();
    }

}
