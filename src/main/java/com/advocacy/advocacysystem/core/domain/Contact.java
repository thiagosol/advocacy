package com.advocacy.advocacysystem.core.domain;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import com.advocacy.advocacysystem.core.domain.enums.TypeContact;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact extends BaseModel<Contact> {

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
    public void update(Contact contactUpdate) {
        this.value = contactUpdate.getValue();
        this.description = contactUpdate.getDescription();
        this.typeContact = contactUpdate.getTypeContact();
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
