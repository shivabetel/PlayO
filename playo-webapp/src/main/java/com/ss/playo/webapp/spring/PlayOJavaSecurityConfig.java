package com.ss.playo.webapp.spring;


import com.ss.playo.webapp.web.filter.JWTAuthenticationFilter;
import com.ss.playo.webapp.web.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@ComponentScan("com.ss.playo.webapp.security")
public class PlayOJavaSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsService userDetailsService;

    public PlayOJavaSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Autowired
    protected void configureGlobalAuth(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.
                cors().
                and().
         authorizeRequests().
                antMatchers("/api/health", "/api/login", "/api/user/**").permitAll().
                anyRequest().authenticated().and().
               // addFilter(new JWTAuthenticationFilter(authenticationManager())).
                addFilter(new JWTAuthorizationFilter(authenticationManager())).
             //   httpBasic().and().
                sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.NEVER).and().
                csrf().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization","Origin","Content-Type"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }




}
