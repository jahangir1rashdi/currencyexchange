package com.ecb.exchangemarket.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    static Map<String, User> users = new HashMap<>();
    @PostConstruct
    public void init() {
        users.put("jahangir",  new User("jahangir", "jahangir", new ArrayList<>()));
        users.put("admin",  new User("admin", "admin", new ArrayList<>() ));
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return users.get(s) ;
    }
}
