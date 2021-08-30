package com.advocacy.advocacysystem.core.usecase.customer;

import com.advocacy.advocacysystem.core.domain.Contact;
import com.advocacy.advocacysystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AddContactToCustomerUseCase {

    private final CustomerGateway customerGateway;

    public void execute(Long customerId, Contact contact){
        var customer = customerGateway.getById(customerId);
        customer.addContacts(contact);
        customerGateway.saveContacts(customer);
    }

}
