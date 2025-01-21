package com.back.cookbook.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Classe que ajuda a desasbilitar as configurações padrão do Spring Security e habilitar as de preferência
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter security_filter;

    /** 
     * Especifica a corrente de filtros aplicados na requisição para realizar validações do usuário
     * */ 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http_security) throws Exception {
        return http_security
            .csrf(csrf -> csrf.disable())   // desabilitando config padrão
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))   // Habilitando autenticação stateless
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario/buscar").hasRole("ADMIN")
                .anyRequest().authenticated()   // demais requisições precisam apenas de autenticação comum com usuário e senha
            )
            .addFilterBefore(security_filter, UsernamePasswordAuthenticationFilter.class)    // Passa esse filtro de autenticação antes de realizar as verificação da requisição
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
