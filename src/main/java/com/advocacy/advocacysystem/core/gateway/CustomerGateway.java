package com.advocacy.advocacysystem.core.gateway;

import com.advocacy.advocacysystem.core.domain.Customer;

public interface CustomerGateway extends BaseGateway<Customer> {

    void saveContacts(Customer customer);

}
