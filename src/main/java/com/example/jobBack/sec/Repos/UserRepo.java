package com.example.jobBack.sec.Repos;

import com.example.jobBack.sec.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
