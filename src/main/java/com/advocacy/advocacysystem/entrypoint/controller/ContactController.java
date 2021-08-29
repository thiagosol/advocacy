package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.usecase.contact.RemoveContactsUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController("ContactController")
@RequestMapping("/v1/contacts")
@Api("Contatos")
public class ContactController {

    private final RemoveContactsUseCase removeContactsToCustomerUseCase;

    @ApiOperation(value = "Remove contatos")
    @DeleteMapping()
    public ResponseEntity<Void> removeContacts(@RequestBody Set<Long> contactIds){
        removeContactsToCustomerUseCase.execute(contactIds);
        return ResponseEntity.ok().build();
    }
}
