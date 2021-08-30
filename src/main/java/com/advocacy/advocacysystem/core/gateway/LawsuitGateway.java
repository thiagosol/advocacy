package com.advocacy.advocacysystem.core.gateway;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LawsuitGateway {

    public Lawsuit save(Lawsuit lawsuit);

    public void update(Lawsuit lawsuit);

    public Lawsuit getById(Long id);

    public Page<Lawsuit> getAll(Pageable pageable);
}
