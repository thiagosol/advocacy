package com.advocacy.advocacysystem.core.domain;

import com.advocacy.advocacysystem.core.domain.base.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Getter
@Entity
public class User extends BaseModel<User> implements UserDetails {

    @Setter
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
        this.createdAt = LocalDateTime.now();
    }

    public User(){}

    public User(String userName, String password, PasswordEncoder passwordEncoder) {
        this.user = userName;
        this.password = passwordEncoder.encode(password);
        this.createdAt = LocalDateTime.now();
    }

    public void setPassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    @Override
    public void update(User userUpdate) {
        if(Objects.nonNull(userUpdate.getUser())) this.user = userUpdate.getUser();
        if(Objects.nonNull(userUpdate.getPassword())) this.password = userUpdate.getPassword();
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
