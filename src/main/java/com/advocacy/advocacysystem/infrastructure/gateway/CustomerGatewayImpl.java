package com.advocacy.advocacysystem.infrastructure.gateway;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.gateway.CustomerGateway;
import com.advocacy.advocacysystem.infrastructure.repository.BaseRepository;
import com.advocacy.advocacysystem.infrastructure.repository.ContactRepository;
import com.advocacy.advocacysystem.infrastructure.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerGatewayImpl extends BaseGatewayImpl<Customer> implements CustomerGateway {

    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    public CustomerGatewayImpl(CustomerRepository customerRepository,
                               ContactRepository contactRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public void saveContacts(Customer customer) {
        contactRepository.saveAll(customer.getContacts());
    }
}
