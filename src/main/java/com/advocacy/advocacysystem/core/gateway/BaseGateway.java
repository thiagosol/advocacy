package com.advocacy.advocacysystem.core.gateway;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseGateway<T extends BaseModel> {

    T save(T source);

    void update(T source);

    void remove(Long id);

    T getById(Long id);

    Page<T> getAll(Pageable pageable);
}
