package com.advocacy.advocacysystem.entrypoint.dto.customer;

import com.advocacy.advocacysystem.core.domain.Customer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CustomerUpdateDTO {

    @ApiModelProperty("Nome do Cliente")
    private String name;

    @ApiModelProperty("Cpf ou CNPJ do Cliente")
    private String cpfCnpj;

    public Customer toCustomer(Long customerId) {
        return new Customer(customerId, this.name, this.cpfCnpj, null, null, LocalDateTime.now());
    }
}
