package com.advocacy.advocacysystem.core.domain;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseModel<User> implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
