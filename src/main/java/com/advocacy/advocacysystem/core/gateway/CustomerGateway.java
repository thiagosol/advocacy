package com.advocacy.advocacysystem.core.gateway;

import com.advocacy.advocacysystem.core.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface CustomerGateway {

    public Customer save(Customer customer);

    public void update(Customer customer);

    public Customer getById(Long id);

    public Page<Customer> getAll(Pageable pageable);

    void saveContacts(Customer customer);
}
