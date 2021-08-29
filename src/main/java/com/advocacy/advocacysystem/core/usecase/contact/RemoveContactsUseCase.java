package com.advocacy.advocacysystem.core.usecase.contact;

import com.advocacy.advocacysystem.core.domain.Contact;
import com.advocacy.advocacysystem.core.gateway.ContactGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RemoveContactsUseCase {

    private final ContactGateway contactGateway;

    public void execute(Set<Long> contactIds){
        var contacts = contactIds.stream().map(Contact::new).collect(Collectors.toSet());
        contactGateway.remove(contacts);
    }

}
