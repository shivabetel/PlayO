package com.ss.playo.webapp.web.contoller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ss.playo.webapp.web.dtos.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity login(@RequestBody ApplicationUser user){
       Authentication authResult = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        String token = JWT.create()
                .withSubject(((User)authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .sign(Algorithm.HMAC512("SecretKeyToGenJWTs".getBytes()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization","BEARER "+token);
        return ResponseEntity.ok().headers(httpHeaders).build();
    }
}
