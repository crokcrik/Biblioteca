package com.example.Biblioteca;

import com.example.Biblioteca.model.User;
import com.example.Biblioteca.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}
    @Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				// Usiamo il passwordEncoder per criptarla!
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setRoles(Set.of("ROLE_ADMIN"));
				userRepository.save(admin);
				System.out.println("Utente ADMIN creato: admin / admin123");
			}
		};
	}

}