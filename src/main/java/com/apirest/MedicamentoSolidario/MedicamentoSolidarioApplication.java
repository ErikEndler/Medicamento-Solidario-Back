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
//	 @Bean
//	    public WebMvcConfigurer corsConfigurer() {
//	        return new WebMvcConfigurerAdapter() {
//	            @Override
//	            public void addCorsMappings(CorsRegistry registry) {
//	                registry.addMapping("/**")
//	                               .allowedOrigins("*")
//	                               .allowedMethods("GET", "POST", "PUT", "DELETE")
//	                               .allowedHeaders("Content-Type", "Authorization");
//	            }
//	        };
//	    }
}
