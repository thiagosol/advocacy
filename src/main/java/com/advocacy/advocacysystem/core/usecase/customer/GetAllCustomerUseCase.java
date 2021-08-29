package com.advocacy.advocacysystem.core.usecase.customer;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllCustomerUseCase {

    private final CustomerGateway customerGateway;

    public Page<Customer> execute(Pageable pageable){
        return customerGateway.getAll(pageable);
    }
}
