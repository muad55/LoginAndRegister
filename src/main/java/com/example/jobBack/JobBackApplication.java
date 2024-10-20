package com.example.jobBack;

import com.example.jobBack.sec.Entities.AppRole;
import com.example.jobBack.sec.Entities.AppUser;
import com.example.jobBack.sec.Services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JobBackApplication{

	public static void main(String[] args) {
		SpringApplication.run(JobBackApplication.class, args);
	}
	//pour encoder le mot de passe
	//Bycrypt est plus fort que MD5
	//c'est pas de la cryptographie mais seulement hashage
	//c'est une algo qui est pas symétrique car a partir de hash en ne peut pas trouver le mot de passe original

	@Bean
	CommandLineRunner start(CompteService compteService){
		return args -> {
			compteService.addNewRole(new AppRole(null,"USER"));
			compteService.addNewRole(new AppRole(null,"ADMIN"));
			compteService.addNewRole(new AppRole(null,"PRODUCT_MANAGER"));

			compteService.addNewUser(new AppUser(null,"user1","1234",new ArrayList<>()));
			compteService.addNewUser(new AppUser(null,"admin","1234",new ArrayList<>()));
			compteService.addNewUser(new AppUser(null,"user2","1234",new ArrayList<>()));
			compteService.addNewUser(new AppUser(null,"user3","1234",new ArrayList<>()));

			compteService.addRoleToUser("user1","USER");
			compteService.addRoleToUser("user2","USER");
			compteService.addRoleToUser("user3","USER");
			compteService.addRoleToUser("user3","PRODUCT_MANAGER");
			compteService.addRoleToUser("admin","ADMIN");
			compteService.addRoleToUser("admin","USER");
		};
	}
}
