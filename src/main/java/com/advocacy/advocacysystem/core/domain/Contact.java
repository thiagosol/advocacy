package com.advocacy.advocacysystem.core.domain;

import com.advocacy.advocacysystem.core.domain.enums.TypeContact;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String value;
    private String description;
    private TypeContact typeContact;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    public Contact(Long id){
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
