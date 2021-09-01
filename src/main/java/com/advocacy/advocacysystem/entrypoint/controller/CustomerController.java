package com.advocacy.advocacysystem.entrypoint.controller;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.usecase.base.CreateBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.base.GetAllBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.base.GetBaseByIdUseCase;
import com.advocacy.advocacysystem.core.usecase.base.UpdateBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.customer.AddContactToCustomerUseCase;
import com.advocacy.advocacysystem.entrypoint.dto.customer.ContactCreateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.customer.CustomerCreateDTO;
import com.advocacy.advocacysystem.entrypoint.dto.customer.CustomerUpdateDTO;
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

@RequiredArgsConstructor
@RestController("CustomerController")
@RequestMapping("/v1/customers")
@Api("Clientes")
public class CustomerController {

    private final CreateBaseUseCase<Customer> createCustomerUseCase;
    private final UpdateBaseUseCase<Customer> updateCustomerUseCase;
    private final GetAllBaseUseCase<Customer> getAllCustomerUseCase;
    private final GetBaseByIdUseCase<Customer> getCustomerByIdUseCase;
    private final AddContactToCustomerUseCase addContactToCustomerUseCase;


    @ApiOperation(value = "Retorna todos os clientes", response = Page.class)
    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers(@PageableDefault(direction = Sort.Direction.ASC, sort = "name") Pageable page){
        return ResponseEntity.status(HttpStatus.CREATED).body(getAllCustomerUseCase.execute(page));
    }

    @ApiOperation(value = "Retorna o cliente por id", response = Customer.class)
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long customerId){
        return ResponseEntity.status(HttpStatus.CREATED).body(getCustomerByIdUseCase.execute(customerId));
    }

    @ApiOperation(value = "Cria novo cliente", response = Customer.class)
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerCreateDTO customerCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(createCustomerUseCase.execute(customerCreateDTO.toCustomer()));
    }

    @ApiOperation(value = "Atualiza cliente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("id") Long customerId,
                                               @RequestBody CustomerUpdateDTO customerUpdateDTO){
        updateCustomerUseCase.execute(customerUpdateDTO.toCustomer(customerId));
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Adiciona Contatos ao cliente")
    @PostMapping("/{id}/add-contact")
    public ResponseEntity<Void> addContacts(@PathVariable("id") Long customerId,
                                               @RequestBody ContactCreateDTO contact){
        addContactToCustomerUseCase.execute(customerId, contact.toContact());
        return ResponseEntity.ok().build();
    }
}
