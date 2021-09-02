package com.advocacy.advocacysystem.core.domain;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseModel<User> {

    private String user;

    @JsonIgnore
    private String password;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Lawyer lawyer;

    private LocalDateTime createdAt;

    public User(User userUpdate){
        this.id = userUpdate.getId();
        this.user = userUpdate.getUser();
        this.password = userUpdate.getPassword();
        this.createdAt = userUpdate.getCreatedAt();
    }

    public User(Long id) {
        this.id = id;
    }

    @Override
    public void update(User lawyerUpdate) {
        this.password = lawyerUpdate.getPassword();
        this.user = lawyerUpdate.getUser();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User customer = (User) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
