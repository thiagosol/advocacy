package com.advocacy.advocacysystem.core.usecase.customer;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public Customer execute(Customer customerIn){
        var customer = new Customer(customerIn);
        return customerGateway.save(customer);
    }
}
