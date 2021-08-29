package com.advocacy.advocacysystem.core.usecase.customer;

import com.advocacy.advocacysystem.core.domain.Contact;
import com.advocacy.advocacysystem.core.gateway.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AddContactsToCustomerUseCase {

    private final CustomerGateway customerGateway;

    public void execute(Long customerId, Set<Contact> contacts){
        var customer = customerGateway.getById(customerId);
        customer.addContacts(contacts);
        customerGateway.save(customer);
    }

}
