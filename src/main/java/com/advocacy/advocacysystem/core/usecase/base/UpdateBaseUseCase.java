package com.advocacy.advocacysystem.core.usecase.base;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import com.advocacy.advocacysystem.core.gateway.BaseGateway;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateBaseUseCase<T extends BaseModel> {

    private BaseGateway<T> baseGateway;

    public void execute(T sourceUpdate){
        var source = baseGateway.getById(sourceUpdate.getId());
        source.update(sourceUpdate);
        baseGateway.update(source);
    }
}
