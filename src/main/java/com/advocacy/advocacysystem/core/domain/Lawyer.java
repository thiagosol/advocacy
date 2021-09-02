package com.advocacy.advocacysystem.core.domain;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lawyer extends BaseModel<Lawyer> {

    private String name;

    private String cpf;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    private LocalDateTime createdAt;

    public Lawyer(Lawyer lawyer){
        this.id = lawyer.getId();
        this.name = lawyer.getName();
        this.cpf = lawyer.getCpf();
        this.createdAt = lawyer.getCreatedAt();
    }

    public Lawyer(Long id) {
        this.id = id;
    }

    @Override
    public void update(Lawyer lawyerUpdate) {
        this.cpf = lawyerUpdate.getCpf();
        this.name = lawyerUpdate.getName();

        if(Objects.nonNull(lawyerUpdate.getUser())){
            var userUpdate = lawyerUpdate.getUser();
            if(Objects.nonNull(userUpdate.getUser())) this.user.setUser(userUpdate.getUser());
            if(Objects.nonNull(userUpdate.getPassword())) this.user.setPassword(userUpdate.getPassword());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lawyer customer = (Lawyer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
