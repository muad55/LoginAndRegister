package com.example.jobBack.sec.Services;

import com.example.jobBack.sec.Entities.AppRole;
import com.example.jobBack.sec.Entities.AppUser;

import java.util.List;

public interface CompteService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    AppRole addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}
