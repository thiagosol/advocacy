package com.advocacy.advocacysystem.infrastructure.command;

import com.advocacy.advocacysystem.core.domain.Lawyer;
import com.advocacy.advocacysystem.core.domain.User;
import com.advocacy.advocacysystem.infrastructure.repository.LawyerRepository;
import com.advocacy.advocacysystem.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile("test")
public class DataLoaderTestCommand implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LawyerRepository lawyerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        insertLawyer();
    }

    private void insertLawyer() {
        String password = "12345";
        var user = new User("advogado0", "12345", passwordEncoder);
        var lawyer = new Lawyer("Advogado 0", "32486945059", user, LocalDateTime.now());
        var userOptional = userRepository.findByUser(user.getUsername());
        if(userOptional.isEmpty()){
            lawyerRepository.save(lawyer);
        }
    }
}
