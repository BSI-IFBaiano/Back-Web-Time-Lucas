package com.web.desenvolvimento.edusphere.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsImpl userDetailsService;

    @Autowired
    private SecurityFilter securityFilter; // Injeção correta do filtro

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/edusphere/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/edusphere/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,
                                "/edusphere/users/register",
                                "/edusphere/managers/register",
                                "/edusphere/departments/register",
                                "/edusphere/teachers/register",
                                "/edusphere/courses/register",
                                "/edusphere/students/register")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,
                                "/edusphere/courses/register",
                                "/edusphere/subjects/register")
                        .hasRole("MANAGER")
                        .anyRequest().permitAll()
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
