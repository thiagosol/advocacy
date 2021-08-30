package com.advocacy.advocacysystem.core.usecase.lawsuit;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.domain.Lawsuit;
import com.advocacy.advocacysystem.core.gateway.CustomerGateway;
import com.advocacy.advocacysystem.core.gateway.LawsuitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateLawsuitUseCase {

    private final LawsuitGateway lawsuitGateway;

    public Lawsuit execute(Lawsuit lawsuit){
        return lawsuitGateway.save(lawsuit);
    }
}
