package com.back.cookbook.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.back.cookbook.dataac.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    @Autowired
    TokenService token_service;

    @Autowired
    UserRepository user_repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        var token = this.recoverToken(request);

        // Caso haja token, há um processamento do token para autenticação do usuário
        if(token != null){
            var login = token_service.validateToken(token);
            UserDetails user = user_repository.findByLogin(login);
            System.out.println(user.getAuthorities());
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());    // Gera uma autenticação para o Spring Security com todas as informações para fazer as autorizações posteriores a depender da role
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // Caso não haja, chama o próximo filtro da corrente de filtros sem autenticar o usuário
        filterChain.doFilter(request, response);
    }   

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
