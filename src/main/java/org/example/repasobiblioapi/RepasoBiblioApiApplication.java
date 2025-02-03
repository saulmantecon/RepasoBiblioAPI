package org.example.repasobiblioapi;

import org.example.repasobiblioapi.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class RepasoBiblioApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepasoBiblioApiApplication.class, args);
    }


    @Configuration
    public static class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(HttpMethod.POST, "/user").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/biblioteca/**").permitAll()
                            .requestMatchers( "/doc/**").permitAll()
                            .requestMatchers( "/v3/**").permitAll() //v3 documentos (info)
                            .anyRequest().authenticated() // Todo lo demás requiere autenticación
                    );
            return http.build();
        }
    }



}
