package com.advocacy.advocacysystem.infrastructure.service;

import com.advocacy.advocacysystem.core.domain.User;
import com.advocacy.advocacysystem.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUserName(String userName){
        Optional<User> user = userRepository.findByUser(userName);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found with user name: " + userName));
    }


}
