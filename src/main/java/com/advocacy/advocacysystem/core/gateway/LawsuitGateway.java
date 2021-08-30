package com.advocacy.advocacysystem.core.gateway;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LawsuitGateway {

    Lawsuit save(Lawsuit lawsuit);

    void update(Lawsuit lawsuit);

    Lawsuit getById(Long id);

    Page<Lawsuit> getAll(Pageable pageable);

    Page<Lawsuit> getByCustomerId(Long customerId, Pageable pageable);
}
