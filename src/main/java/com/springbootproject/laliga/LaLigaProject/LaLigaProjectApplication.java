package com.springbootproject.laliga.LaLigaProject;

import com.springbootproject.laliga.LaLigaProject.service.ServiceTeam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class LaLigaProjectApplication {

	public static void main(String[] args) {
	/*	File file=new File("C:\\Users\\mog\\Desktop\\Trinee\\Coole Great Projecten\\LaLiga-Project\\LaLiga-Project\\src\\main\\resources\\laliga_matches.csv");
		if(file.exists()){
			System.out.println("yes");
		}
		else {
			System.out.println("nein");
		}*/

		SpringApplication.run(LaLigaProjectApplication.class, args);
	}

}
