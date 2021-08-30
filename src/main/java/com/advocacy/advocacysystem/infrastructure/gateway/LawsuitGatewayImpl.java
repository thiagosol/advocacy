package com.advocacy.advocacysystem.infrastructure.gateway;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import com.advocacy.advocacysystem.core.gateway.LawsuitGateway;
import com.advocacy.advocacysystem.infrastructure.exception.ObjectNotExistException;
import com.advocacy.advocacysystem.infrastructure.repository.LawsuitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LawsuitGatewayImpl implements LawsuitGateway {

    private final LawsuitRepository lawsuitRepository;

    @Override
    public Lawsuit save(Lawsuit lawsuit) {
        lawsuitRepository.save(lawsuit);
        return lawsuit;
    }

    @Override
    public void update(Lawsuit lawsuit) {
        lawsuitRepository.save(lawsuit);
    }

    @Override
    public Lawsuit getById(Long id) {
        return lawsuitRepository.findById(id).orElseThrow(() -> new ObjectNotExistException(Lawsuit.class.getName(), id));
    }

    @Override
    public Page<Lawsuit> getAll(Pageable pageable) {
        return lawsuitRepository.findAll(pageable);
    }
}
