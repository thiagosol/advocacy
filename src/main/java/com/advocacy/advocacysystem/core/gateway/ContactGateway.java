package com.advocacy.advocacysystem.core.gateway;

import com.advocacy.advocacysystem.core.domain.Contact;

import java.util.Set;

public interface ContactGateway {

    public void remove(Set<Contact> contacts);
}