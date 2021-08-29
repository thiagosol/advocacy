package com.advocacy.advocacysystem.infrastructure.gateway;

import com.advocacy.advocacysystem.core.domain.Contact;
import com.advocacy.advocacysystem.core.gateway.ContactGateway;
import com.advocacy.advocacysystem.infrastructure.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ContactGatewayImpl implements ContactGateway {

    private final ContactRepository contactRepository;

    @Override
    public void remove(Set<Contact> contacts) {
        contactRepository.deleteAll(contacts);
    }
}
