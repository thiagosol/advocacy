package com.advocacy.advocacysystem.core.gateway;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseGateway<T extends BaseModel> {

    public T save(T source);

    public void update(T source);

    public void remove(Long id);

    public T getById(Long id);

    public Page<T> getAll(Pageable pageable);
}
