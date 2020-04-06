//package com.ss.playo.webapp.spring;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class PlayOJavaSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    UserDetailsService userDetailsService;
//
//    public PlayOJavaSecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//
//    @Autowired
//    protected void configureGlobalAuth(AuthenticationManagerBuilder auth) throws Exception {
//       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.
//         authorizeRequests().
//                antMatchers("/api/health").anonymous().
//                anyRequest().authenticated().and().
//                httpBasic().and().
//                sessionManagement().
//                sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
//                csrf().disable();
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//}
