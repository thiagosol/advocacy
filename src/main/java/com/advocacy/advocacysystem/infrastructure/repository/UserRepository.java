package com.advocacy.advocacysystem.infrastructure.repository;

import com.advocacy.advocacysystem.core.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUser(String userName);
}
