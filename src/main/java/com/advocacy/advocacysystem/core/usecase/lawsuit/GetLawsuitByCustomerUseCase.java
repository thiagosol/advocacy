package com.advocacy.advocacysystem.core.usecase.lawsuit;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import com.advocacy.advocacysystem.core.gateway.LawsuitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetLawsuitByCustomerUseCase {

    private final LawsuitGateway lawsuitGateway;

    public Page<Lawsuit> execute(Long customerId, Pageable pageable){
        return lawsuitGateway.getByCustomerId(customerId, pageable);
    }
}
