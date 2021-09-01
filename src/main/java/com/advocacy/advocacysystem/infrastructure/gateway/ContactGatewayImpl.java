package com.advocacy.advocacysystem.infrastructure.gateway;

import com.advocacy.advocacysystem.core.domain.Contact;
import com.advocacy.advocacysystem.core.gateway.ContactGateway;
import com.advocacy.advocacysystem.infrastructure.repository.ContactRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ContactGatewayImpl extends BaseGatewayImpl<Contact> implements ContactGateway {

    private final ContactRepository contactRepository;

    public ContactGatewayImpl(ContactRepository contactRepository) {
        super(contactRepository);
        this.contactRepository = contactRepository;
    }

    @Override
    public void remove(Set<Contact> contacts) {
        contactRepository.deleteAll(contacts);
    }
}
