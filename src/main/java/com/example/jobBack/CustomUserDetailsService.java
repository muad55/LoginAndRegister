package com.example.jobBack;

import com.example.jobBack.sec.Entities.AppUser;
import com.example.jobBack.sec.Repos.UserRepo;
import com.example.jobBack.sec.Services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    @Lazy
    private CompteService compteService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = compteService.loadUserByUsername(username);
        if(appUser==null){
            throw new UsernameNotFoundException("User not found");
        }
        //pour retourner UserDetails il faut poser les roles dans une list de types GrantedAuthority
        // Convertir les rôles stockés en une liste d'objets GrantedAuthority
        Collection<GrantedAuthority> authorities = appUser.getAppRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                authorities);
    }
}
