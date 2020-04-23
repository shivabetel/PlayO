package com.ss.playo.webapp.security;

import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayOUserDetailsService implements UserDetailsService {

    IUserService userService;

    public PlayOUserDetailsService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user =  this.userService.findByEmailId_Enabled(userId).orElseGet(() -> null);
        if(user == null){
            throw new UsernameNotFoundException("User with email id "+userId+" doesn't exist");
        }

        final List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoleSet().forEach(role -> {
            authorities.addAll(role.getPrivilegeSet().stream().map(privilege -> new SimpleGrantedAuthority(privilege.getName())).distinct().collect(Collectors.toList()));
        });

        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), authorities);
    }
}
