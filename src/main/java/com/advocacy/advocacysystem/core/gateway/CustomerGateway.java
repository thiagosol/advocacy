package com.advocacy.advocacysystem.core.gateway;

import com.advocacy.advocacysystem.core.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface CustomerGateway extends BaseGateway<Customer> {

    void saveContacts(Customer customer);

}
