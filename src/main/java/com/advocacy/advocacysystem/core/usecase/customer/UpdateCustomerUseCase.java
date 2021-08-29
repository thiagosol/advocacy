package com.advocacy.advocacysystem.core.usecase.customer;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public void execute(Customer customerUpdate){
        var customer = customerGateway.getById(customerUpdate.getId());
        customerUpdate.setContacts(customer.getContacts());
        customerGateway.update(customerUpdate);
    }

}
