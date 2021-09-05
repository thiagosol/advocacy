package com.advocacy.advocacysystem.entrypoint.dto.customer;

import com.advocacy.advocacysystem.core.domain.Customer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateDTO {

    @ApiModelProperty("Nome do Cliente")
    private String name;

    @ApiModelProperty("Cpf ou CNPJ do Cliente")
    private String cpfCnpj;

    public Customer toCustomer(Long customerId) {
        var customer = Customer.builder()
                .name(this.name)
                .cpfCnpj(this.cpfCnpj)
                .createdAt(LocalDateTime.now())
                .build();
        customer.setId(customerId);
        return customer;
    }
}
