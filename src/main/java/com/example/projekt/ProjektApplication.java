package com.example.projekt;

import com.example.projekt.model.Role;
import com.example.projekt.model.User;
import com.example.projekt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ProjektApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektApplication.class, args);
    }

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("adminpass")); // Heslo zakódované pomocí BCrypt
                admin.setRole(Role.ADMIN);
                admin.setEmail("admin@example.com");
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("user") == null) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("userpass")); // Heslo zakódované pomocí BCrypt
                user.setRole(Role.USER);
                user.setEmail("user@example.com");
                userRepository.save(user);
            }
            if (userRepository.findByUsername("xd") == null) {
                User user = new User();
                user.setUsername("xd");
                user.setPassword(passwordEncoder.encode("xd")); // Heslo zakódované pomocí BCrypt
                user.setRole(Role.USER);
                user.setEmail("xd@xd");
                userRepository.save(user);
            }
        };
    }
}
