package com.advocacy.advocacysystem.core.domain;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lawsuit extends BaseModel<Lawsuit> {

    private Long number;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "customer_lawsuit",
            joinColumns = { @JoinColumn(name = "lawsuit_id") },
            inverseJoinColumns = { @JoinColumn(name = "customer_id") }
    )
    Set<Customer> customers = new HashSet<>();

    private LocalDateTime createdAt;

    @Override
    public void update(Lawsuit lawsuitUpdate) {
        this.number = lawsuitUpdate.getNumber();
        this.description = lawsuitUpdate.getDescription();
    }

    public void addCustomers(Set<Customer> customers) {
        this.customers.addAll(customers);
    }

    public void removeCustomers(Set<Customer> customers) {
        this.customers.removeAll(customers);
    }
}
