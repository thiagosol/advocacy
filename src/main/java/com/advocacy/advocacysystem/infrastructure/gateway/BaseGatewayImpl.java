package com.advocacy.advocacysystem.infrastructure.gateway;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import com.advocacy.advocacysystem.core.gateway.BaseGateway;
import com.advocacy.advocacysystem.infrastructure.exception.ObjectNotExistException;
import com.advocacy.advocacysystem.infrastructure.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class BaseGatewayImpl<T extends BaseModel> implements BaseGateway<T> {

    protected final BaseRepository<T> baseRepository;

    @Override
    public T save(T source) {
        baseRepository.save(source);
        return source;
    }

    @Override
    public void update(T source) {
        baseRepository.save(source);
    }

    @Override
    public void remove(Long id) {
        baseRepository.deleteById(id);
    }

    @Override
    public T getById(Long id) {
        return baseRepository.findById(id).orElseThrow(() -> new ObjectNotExistException(id));
    }

    @Override
    public Page<T> getAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }

}
