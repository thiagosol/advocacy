package com.advocacy.advocacysystem.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private String cpfCnpj;

    @OneToMany(mappedBy = "customer")
    private Set<Contact> contacts = new HashSet<>();

    @ManyToMany(mappedBy = "customers")
    Set<Lawsuit> lawsuits = new HashSet<>();

    private LocalDateTime createdAt;

    public Customer(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.cpfCnpj = customer.getCpfCnpj();
        this.contacts = customer.getContacts();
        this.contacts.forEach(contact -> contact.setCustomer(this));
        this.createdAt = customer.getCreatedAt();
        this.updateReferenceContacts();
    }

    public Customer(Long id) {
        this.id = id;
    }

    public Set<Contact> getContacts() {
        return Collections.unmodifiableSet(contacts);
    }

    public void addContacts(Contact contact) {
        this.contacts.add(contact);
        this.updateReferenceContacts();
    }

    public void removeContacts(Set<Contact> contacts) {
        this.contacts.removeAll(contacts);
    }

    private void updateReferenceContacts(){
        this.contacts.forEach(contact -> contact.setCustomer(this));
    }

    public void update(Customer customerUpdate) {
        this.cpfCnpj = customerUpdate.getCpfCnpj();
        this.name = customerUpdate.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
