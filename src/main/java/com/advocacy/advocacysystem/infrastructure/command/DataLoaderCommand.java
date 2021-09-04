package com.advocacy.advocacysystem.infrastructure.command;

import com.advocacy.advocacysystem.core.domain.User;
import com.advocacy.advocacysystem.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoaderCommand implements CommandLineRunner {

    @Value("${advocacy.user-admin.name}")
    private String userAdminName;

    @Value("${advocacy.user-admin.password}")
    private String userAdminPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        insertUserAdm();
    }

    private void insertUserAdm() {
        var user = new User(userAdminName, userAdminPassword, passwordEncoder);
        var userOptional = userRepository.findByUser(user.getUsername());
        if(userOptional.isEmpty()){
            userRepository.save(user);
        }
    }
}
