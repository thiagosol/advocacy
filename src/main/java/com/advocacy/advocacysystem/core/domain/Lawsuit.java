package com.advocacy.advocacysystem.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
public class Lawsuit {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

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
