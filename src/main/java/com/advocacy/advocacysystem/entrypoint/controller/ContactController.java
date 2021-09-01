package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.domain.Contact;
import com.advocacy.advocacysystem.core.usecase.base.RemoveBaseUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("ContactController")
@RequestMapping("/v1/contacts")
@Api("Contatos")
public class ContactController {

    private final RemoveBaseUseCase<Contact> removeContactUseCase;

    @ApiOperation(value = "Remove contato")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeContact(@PathVariable("id") Long contactId){
        removeContactUseCase.execute(contactId);
        return ResponseEntity.ok().build();
    }
}
