package com.emrecan.workoutplanner.microservices.user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
                        .disable() // Disable CSRF for simplicity in this example
                )
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/**").permitAll()
                        .anyExchange().authenticated()
                );

        return http.build();
    }

}
