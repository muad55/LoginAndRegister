package com.example.jobBack.sec.Services;


import com.example.jobBack.sec.Entities.AppRole;
import com.example.jobBack.sec.Entities.AppUser;
import com.example.jobBack.sec.Repos.RoleRepo;
import com.example.jobBack.sec.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CompteServiceImp implements CompteService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw=appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return userRepo.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return roleRepo.save(appRole);
    }

    @Override
    public AppRole addRoleToUser(String username, String roleName) {
        AppUser appuser=userRepo.findByUsername(username);
        AppRole approle=roleRepo.findByRoleName(roleName);
        appuser.getAppRoles().add(approle);
        return null;
    }
    @Override
    public AppUser loadUserByUsername(String username) {
          return userRepo.findByUsername(username);
    }
    @Override
    public List<AppUser> listUsers() {
        return userRepo.findAll() ;
    }
}
