package com.gabriel.ferreira.ms_customer.infra.config.modelMapper.security;

import com.gabriel.ferreira.ms_customer.application.interfaces.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter {

    private  final TokenService _tokenService;
    private final IUserService _userService;

    public SecurityFilter(TokenService tokenService, IUserService userService) {
        _tokenService = tokenService;
        _userService = userService;
    }


    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null){
            return null ;
        }
        return authHeader.replace("Bearer ", "");
    }
}
