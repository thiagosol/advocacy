package com.advocacy.advocacysystem.infrastructure.config.usecase;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.gateway.CustomerGateway;
import com.advocacy.advocacysystem.core.usecase.base.CreateBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.base.GetAllBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.base.GetBaseByIdUseCase;
import com.advocacy.advocacysystem.core.usecase.base.UpdateBaseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CustomerUseCaseConfig {

    private final CustomerGateway customerGateway;

    @Bean
    @Qualifier("createCustomerUseCase")
    public CreateBaseUseCase<Customer> createCustomerUseCase(){
        return new CreateBaseUseCase<Customer>(customerGateway);
    }

    @Bean
    @Qualifier("getAllCustomerUseCase")
    public GetAllBaseUseCase<Customer> getAllCustomerUseCase(){
        return new GetAllBaseUseCase<Customer>(customerGateway);
    }

    @Bean
    @Qualifier("getCustomerByIdUseCase")
    public GetBaseByIdUseCase<Customer> getCustomerByIdUseCase(){
        return new GetBaseByIdUseCase<Customer>(customerGateway);
    }

    @Bean
    @Qualifier("updateCustomerUseCase")
    public UpdateBaseUseCase<Customer> updateCustomerUseCase(){
        return new UpdateBaseUseCase<Customer>(customerGateway);
    }

}
