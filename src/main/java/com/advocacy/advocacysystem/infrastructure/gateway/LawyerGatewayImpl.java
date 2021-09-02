package com.advocacy.advocacysystem.infrastructure.gateway;

import com.advocacy.advocacysystem.core.domain.Lawyer;
import com.advocacy.advocacysystem.core.gateway.LawyerGateway;
import com.advocacy.advocacysystem.infrastructure.repository.LawyerRepository;
import org.springframework.stereotype.Component;

@Component
public class LawyerGatewayImpl extends BaseGatewayImpl<Lawyer> implements LawyerGateway {

    private final LawyerRepository lawyerRepository;

    public LawyerGatewayImpl(LawyerRepository lawyerRepository) {
        super(lawyerRepository);
        this.lawyerRepository = lawyerRepository;
    }

}
