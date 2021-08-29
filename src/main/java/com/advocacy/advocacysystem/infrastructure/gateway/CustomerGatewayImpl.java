package com.advocacy.advocacysystem.infrastructure.gateway;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.gateway.CustomerGateway;
import com.advocacy.advocacysystem.infrastructure.exception.CustomerNotExistException;
import com.advocacy.advocacysystem.infrastructure.repository.ContactRepository;
import com.advocacy.advocacysystem.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    @Override
    public Customer save(Customer customer) {
        contactRepository.saveAll(customer.getContacts());
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotExistException(id));
    }

    @Override
    public Page<Customer> getAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
}
