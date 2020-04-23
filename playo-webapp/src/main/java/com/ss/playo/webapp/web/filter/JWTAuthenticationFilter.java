package com.ss.playo.webapp.web.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.playo.webapp.web.dtos.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private boolean postOnly = true;


    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            if(this.postOnly && !request.getMethod().equals("POST")){
                throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
            }
            else{
              ApplicationUser user = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
               return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            }
        }catch (IOException io){
            throw new RuntimeException(io);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User)authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .sign(Algorithm.HMAC512("SecretKeyToGenJWTs".getBytes()));
        response.addHeader("Authorization", "BEARER "+token);
    }
}
