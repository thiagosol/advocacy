package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.usecase.customer.AddContactsToCustomerUseCase;
import com.advocacy.advocacysystem.core.usecase.customer.CreateCustomerUseCase;
import com.advocacy.advocacysystem.core.usecase.customer.GetAllCustomerUseCase;
import com.advocacy.advocacysystem.core.usecase.customer.UpdateCustomerUseCase;
import com.advocacy.advocacysystem.entrypoint.dto.ContactCreateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.CustomerCreateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.CustomerUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController("CustomerController")
@RequestMapping("/v1/customers")
@Api("Clientes")
public class CustomerController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final AddContactsToCustomerUseCase addContactsToCustomerUseCase;
    private final GetAllCustomerUseCase getAllCustomerUseCase;

    @ApiOperation(value = "Retorna todos os clientes", response = Page.class)
    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomer(@PageableDefault(direction = Sort.Direction.ASC, sort = "name") Pageable page){
        return ResponseEntity.status(HttpStatus.CREATED).body(getAllCustomerUseCase.execute(page));
    }

    @ApiOperation(value = "Cria novo cliente", response = Customer.class)
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerCreateDTO customerCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(createCustomerUseCase.execute(customerCreateDTO.getCustomer()));
    }

    @ApiOperation(value = "Atualiza cliente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") Long customerId,
                                               @RequestBody CustomerUpdateDTO customerUpdateDTO){
        updateCustomerUseCase.execute(customerUpdateDTO.getCustomer(customerId));
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Adiciona Contatos ao cliente")
    @PostMapping("/{id}/add-contacts")
    public ResponseEntity<Void> addContacts(@PathVariable("id") Long customerId,
                                               @RequestBody Set<ContactCreateDTO> contacts){
        var contactsCreate = contacts.stream().map(ContactCreateDTO::getContact).collect(Collectors.toSet());
        addContactsToCustomerUseCase.execute(customerId, contactsCreate);
        return ResponseEntity.ok().build();
    }
}
