package com.izobretatel777.chat.service.impl;

import com.izobretatel777.chat.dao.repo.UserRepo;
import com.izobretatel777.chat.service.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.izobretatel777.chat.dao.entity.User user = userRepo.findByLogin(username);
        if (user == null)
            user = userRepo.findByPhoneNumber(username);
        if (user != null){
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new User(user.getLogin(),user.getPassword(), user.isActive(), true, true, user.isActive(), authorities);
        }
        else {
            throw new UsernameNotFoundException("User not found with username " + username);
        }
    }
}
