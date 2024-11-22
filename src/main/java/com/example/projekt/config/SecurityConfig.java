package com.example.projekt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF if necessary
                .authorizeRequests()
                .requestMatchers("/login", "/register", "/css/**", "/js/**")  // Allow these pages and static resources
                .permitAll()
                .anyRequest().authenticated()  // All other pages need authentication
                .and()
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")  // Custom login page
                        .loginProcessingUrl("/login")  // POST request for login
                        .defaultSuccessUrl("/dashboard", true) // Redirect to dashboard on successful login
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")  // Default logout URL
                        .logoutSuccessUrl("/login")  // Redirect to login page on logout
                        .permitAll());
        return http.build();
    }
}

