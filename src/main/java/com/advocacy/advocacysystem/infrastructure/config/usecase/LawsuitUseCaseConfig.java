package com.advocacy.advocacysystem.infrastructure.config.usecase;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import com.advocacy.advocacysystem.core.gateway.LawsuitGateway;
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
public class LawsuitUseCaseConfig {

    private LawsuitGateway lawsuitGateway;

    @Bean
    @Qualifier("createLawsuitUseCase")
    public CreateBaseUseCase<Lawsuit> createLawsuitUseCase(){
        return new CreateBaseUseCase<Lawsuit>(lawsuitGateway);
    }

    @Bean
    @Qualifier("getAllLawsuitUseCase")
    public GetAllBaseUseCase<Lawsuit> getAllLawsuitUseCase(){
        return new GetAllBaseUseCase<Lawsuit>(lawsuitGateway);
    }

    @Bean
    @Qualifier("getLawsuitByIdUseCase")
    public GetBaseByIdUseCase<Lawsuit> getLawsuitByIdUseCase(){
        return new GetBaseByIdUseCase<Lawsuit>(lawsuitGateway);
    }

    @Bean
    @Qualifier("updateLawsuitUseCase")
    public UpdateBaseUseCase<Lawsuit> updateLawsuitUseCase(){
        return new UpdateBaseUseCase<Lawsuit>(lawsuitGateway);
    }

}
