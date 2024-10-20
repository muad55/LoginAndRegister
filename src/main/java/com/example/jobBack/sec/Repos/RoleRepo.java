package com.example.jobBack.sec.Repos;

import com.example.jobBack.sec.Entities.AppRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
