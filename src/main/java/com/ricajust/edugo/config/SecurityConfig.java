package com.ricajust.edugo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
		.csrf(csrf -> csrf.disable()) // Disable CSRF for APIs (configure properly in production)
		.authorizeHttpRequests(auth -> auth
			.requestMatchers("/api/auth/login", "/public/**").permitAll() // Public endpoints
			.anyRequest().authenticated() // Require authentication for all other endpoints
		)
		.httpBasic(httpBasic -> {}); // Explicitly configure HTTP Basic authentication

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Configurando codificador de senhas
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // Configura o gerenciador de autenticação
    }
}
