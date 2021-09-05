package com.advocacy.advocacysystem.entrypoint.dto.customer;

import com.advocacy.advocacysystem.core.domain.Customer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateDTO {

    @ApiModelProperty("Nome do Cliente")
    private String name;

    @ApiModelProperty("Cpf ou CNPJ do Cliente")
    private String cpfCnpj;

    @ApiModelProperty("Lista de contatos")
    private Set<ContactCreateDTO> contacts = new HashSet<>();

    public Customer toCustomer() {
        var contacts = this.contacts.stream().map(ContactCreateDTO::toContact).collect(Collectors.toSet());
        return Customer.builder()
                .name(this.name)
                .cpfCnpj(this.cpfCnpj)
                .contacts(contacts)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
