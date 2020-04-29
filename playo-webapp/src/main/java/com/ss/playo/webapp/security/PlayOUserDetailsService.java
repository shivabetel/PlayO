package com.ss.playo.webapp.security;

import com.ss.playo.webapp.persistence.dao.model.Privilege;
import com.ss.playo.webapp.persistence.dao.model.Role;
import com.ss.playo.webapp.persistence.dao.model.User;
import com.ss.playo.webapp.service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
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

        Set<Role> roles = user.getRoleSet();
        Set<Privilege> privilegeSet = new HashSet<>();
        for (Role role: roles){
         privilegeSet.addAll(role.getPrivilegeSet());
        }

        Function<Object, String> toStringFunction = Functions.toStringFunction();
        List<String> rolesToString = privilegeSet.stream().map(toStringFunction).collect(Collectors.toList());

        final List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(rolesToString.toArray(new String[rolesToString.size()]));//new ArrayList<>();
//        user.getRoleSet().forEach(role -> {
//            authorities.addAll(role.getPrivilegeSet().stream().map(privilege -> new SimpleGrantedAuthority(privilege.getName())).distinct().collect(Collectors.toList()));
//        });

        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), authorities);
    }

    static final class  Functions {

      static Function<Object, String> toStringFunction(){
          return ToStringFunction.INSTANCE;
      }


         static enum ToStringFunction implements Function<Object, String> {
            INSTANCE;

            @Override
            public String apply(Object o) {
                if(o == null){
                    throw new NullPointerException();
                }
                return o.toString();
            }
        }

    }


}
