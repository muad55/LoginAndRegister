package com.example.jobBack.sec.Web;


import com.example.jobBack.sec.Entities.AppRole;
import com.example.jobBack.sec.Entities.AppUser;
import com.example.jobBack.sec.Services.CompteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableMethodSecurity(prePostEnabled = true)
@RestController
public class CompteRestController {
    @Autowired
    private CompteService compteService;

    @GetMapping(path = "/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public List<AppUser> appUsers(){
        return compteService.listUsers();
    }
    @PostMapping(path = "/users")
    public AppUser saveUser(@RequestBody AppUser appUser){
        return compteService.addNewUser(appUser);
    }
    @PostMapping(path = "/roles")
    public AppRole saveRole(@RequestBody AppRole appRole){
        return compteService.addNewRole(appRole);
    }
    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
        compteService.addRoleToUser(roleUserForm.getUsername(),roleUserForm.getRoleName());
    }
}

@Data
class RoleUserForm{
    private String username;
    private String roleName;
}