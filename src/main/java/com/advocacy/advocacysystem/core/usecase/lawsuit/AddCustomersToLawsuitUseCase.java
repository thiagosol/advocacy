package com.advocacy.advocacysystem.core.usecase.lawsuit;

import com.advocacy.advocacysystem.core.domain.Customer;
import com.advocacy.advocacysystem.core.gateway.LawsuitGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AddCustomersToLawsuitUseCase {

    private final LawsuitGateway lawsuitGateway;

    public void execute(Long lawsuitId, Set<Long> consumersId){
        var lawsuit = lawsuitGateway.getById(lawsuitId);
        var customers = consumersId.stream().map(Customer::new).collect(Collectors.toSet());
        lawsuit.addCustomers(customers);
        lawsuitGateway.save(lawsuit);
    }

}
