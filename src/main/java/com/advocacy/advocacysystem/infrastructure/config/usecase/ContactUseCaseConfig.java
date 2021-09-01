package com.advocacy.advocacysystem.infrastructure.config.usecase;

import com.advocacy.advocacysystem.core.domain.Contact;
import com.advocacy.advocacysystem.core.gateway.ContactGateway;
import com.advocacy.advocacysystem.core.usecase.base.RemoveBaseUseCase;
import com.advocacy.advocacysystem.infrastructure.gateway.ContactGatewayImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ContactUseCaseConfig {

    private final ContactGateway contactGateway;

    @Bean
    @Qualifier("removeContactUseCase")
    public RemoveBaseUseCase<Contact> removeContactUseCase(){
        return new RemoveBaseUseCase<Contact>(contactGateway);
    }

}
