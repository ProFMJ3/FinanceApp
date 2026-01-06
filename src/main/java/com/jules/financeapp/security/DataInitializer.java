package com.jules.financeapp.security;



import com.jules.financeapp.entity.User;
import com.jules.financeapp.entity.enums.Role;
import com.jules.financeapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            // Créer un admin
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = User.builder()
                        .username("admin")
                        .email("admin@example.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role(Role.ADMIN)
                        .enabled(true)
                        .build();
                userRepository.save(admin);
                System.out.println("✅ Admin user created: admin / admin123");
            }

            // Créer un CLIENT (pas USER)
            if (userRepository.findByUsername("client").isEmpty()) {
                User client = User.builder()
                        .username("client")
                        .email("client@example.com")
                        .password(passwordEncoder.encode("client123"))
                        .role(Role.CLIENT) // NOTE: CLIENT, pas USER
                        .enabled(true)
                        .build();
                userRepository.save(client);
                System.out.println("✅ CLIENT user created: client / client123");
            }
        };
    }
}