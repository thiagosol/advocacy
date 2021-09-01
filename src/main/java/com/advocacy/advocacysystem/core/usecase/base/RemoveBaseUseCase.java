package com.advocacy.advocacysystem.core.usecase.base;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import com.advocacy.advocacysystem.core.gateway.BaseGateway;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveBaseUseCase<T extends BaseModel> {

    private BaseGateway<T> baseGateway;

    public void execute(Long sourceId){
        baseGateway.remove(sourceId);
    }
}
