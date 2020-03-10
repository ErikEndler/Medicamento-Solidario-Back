package com.apirest.MedicamentoSolidario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicamentoSolidarioApplication //extends SpringBootServletInitializer 
{

	public static void main(String[] args) {
		SpringApplication.run(MedicamentoSolidarioApplication.class, args);
		//System.out.print(new BCryptPasswordEncoder().encode("admin"));
	}
	
	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(MedicamentoSolidarioApplication.class); }
	 */
}
