package com.advocacy.advocacysystem.infrastructure.config.usecase;

import com.advocacy.advocacysystem.core.domain.Lawyer;
import com.advocacy.advocacysystem.core.gateway.LawyerGateway;
import com.advocacy.advocacysystem.core.usecase.base.CreateBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.base.GetAllBaseUseCase;
import com.advocacy.advocacysystem.core.usecase.base.GetBaseByIdUseCase;
import com.advocacy.advocacysystem.core.usecase.base.UpdateBaseUseCase;
import com.advocacy.advocacysystem.infrastructure.gateway.BaseGatewayImpl;
import com.advocacy.advocacysystem.infrastructure.repository.LawyerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LawyerUseCaseConfig {

    private final LawyerGateway lawyerGateway;

    @Bean
    @Qualifier("createLawyerUseCase")
    public CreateBaseUseCase<Lawyer> createLawyerUseCase(){
        return new CreateBaseUseCase<Lawyer>(lawyerGateway);
    }

    @Bean
    @Qualifier("getAllLawyerUseCase")
    public GetAllBaseUseCase<Lawyer> getAllLawyerUseCase(){
        return new GetAllBaseUseCase<Lawyer>(lawyerGateway);
    }

    @Bean
    @Qualifier("getLawyerByIdUseCase")
    public GetBaseByIdUseCase<Lawyer> getLawyerByIdUseCase(){
        return new GetBaseByIdUseCase<Lawyer>(lawyerGateway);
    }

    @Bean
    @Qualifier("updateLawyerUseCase")
    public UpdateBaseUseCase<Lawyer> updateLawyerUseCase(){
        return new UpdateBaseUseCase<Lawyer>(lawyerGateway);
    }

}
