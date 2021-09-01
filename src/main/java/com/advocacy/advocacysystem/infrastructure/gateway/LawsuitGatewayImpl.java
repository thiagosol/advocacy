package com.advocacy.advocacysystem.infrastructure.gateway;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import com.advocacy.advocacysystem.core.gateway.LawsuitGateway;
import com.advocacy.advocacysystem.infrastructure.repository.LawsuitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class LawsuitGatewayImpl extends BaseGatewayImpl<Lawsuit> implements LawsuitGateway {

    private final LawsuitRepository lawsuitRepository;

    public LawsuitGatewayImpl(LawsuitRepository lawsuitRepository) {
        super(lawsuitRepository);
        this.lawsuitRepository = lawsuitRepository;
    }

    @Override
    public Page<Lawsuit> getByCustomerId(Long customerId, Pageable pageable) {
        return lawsuitRepository.findByCustomersId(customerId, pageable);
    }
}
