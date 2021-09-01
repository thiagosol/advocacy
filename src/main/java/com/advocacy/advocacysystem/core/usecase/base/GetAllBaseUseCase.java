package com.advocacy.advocacysystem.core.usecase.base;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import com.advocacy.advocacysystem.core.gateway.BaseGateway;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class GetAllBaseUseCase<T extends BaseModel> {

    private BaseGateway<T> baseGateway;

    public Page<T> execute(Pageable pageable){
        return baseGateway.getAll(pageable);
    }
}
