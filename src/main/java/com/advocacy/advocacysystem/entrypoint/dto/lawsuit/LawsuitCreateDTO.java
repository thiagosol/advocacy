package com.advocacy.advocacysystem.entrypoint.dto.lawsuit;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.domain.Lawsuit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class LawsuitCreateDTO {


    @ApiModelProperty("Número do Processo")
    private Long number;

    @ApiModelProperty("Descrição do Processo")
    private String description;

    @ApiModelProperty("Clientes do Processo")
    Set<Long> customerIds = new HashSet<>();

    public Lawsuit toLawsuit() {
        var customers = this.customerIds.stream().map(Customer::new).collect(Collectors.toSet());
        return Lawsuit.builder()
                .number(number)
                .description(description)
                .customers(customers)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
