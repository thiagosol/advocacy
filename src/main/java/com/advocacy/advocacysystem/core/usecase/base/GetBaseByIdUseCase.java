package com.advocacy.advocacysystem.core.usecase.base;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import com.advocacy.advocacysystem.core.gateway.BaseGateway;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetBaseByIdUseCase<T extends BaseModel> {

    private BaseGateway<T> baseGateway;

    public T execute(Long id){
        return baseGateway.getById(id);
    }
}
