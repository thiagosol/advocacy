package com.advocacy.advocacysystem.core.gateway;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LawsuitGateway extends BaseGateway<Lawsuit> {

    Page<Lawsuit> getByCustomerId(Long customerId, Pageable pageable);

}
