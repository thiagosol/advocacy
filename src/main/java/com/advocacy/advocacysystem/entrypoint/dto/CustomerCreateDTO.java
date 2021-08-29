package com.advocacy.advocacysystem.entrypoint.dto;

import com.advocacy.advocacysystem.core.domain.Customer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CustomerCreateDTO {

    @ApiModelProperty("Nome do Cliente")
    private String name;

    @ApiModelProperty("Cpf ou CNPJ do Cliente")
    private String cpfCnpj;

    @ApiModelProperty("Lista de contatos")
    private Set<ContactCreateDTO> contacts = new HashSet<>();

    public Customer getCustomer() {
        var contacts = this.contacts.stream().map(ContactCreateDTO::getContact).collect(Collectors.toSet());
        return new Customer(null, this.name, this.cpfCnpj, contacts, LocalDateTime.now());
    }
}
