package com.advocacy.advocacysystem.core.usecase.lawsuit;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import com.advocacy.advocacysystem.core.gateway.LawsuitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetLawsuitByIdUseCase {

    private final LawsuitGateway lawsuitGateway;

    public Lawsuit execute(Long lawsuitId){
        return lawsuitGateway.getById(lawsuitId);
    }
}
