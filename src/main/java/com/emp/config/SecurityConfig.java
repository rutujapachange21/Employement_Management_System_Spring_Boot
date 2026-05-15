package com.emp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
    	 http
         .csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(auth -> auth
             .requestMatchers("/api/auth/**").permitAll()
             .anyRequest().authenticated()
         )
         .httpBasic(Customizer.withDefaults());

     return http.build();
    }
	
}
