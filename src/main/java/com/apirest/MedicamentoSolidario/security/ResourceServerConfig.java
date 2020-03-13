package com.apirest.MedicamentoSolidario.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        http.authorizeRequests()
        		.antMatchers(publicEndPoints()).permitAll() 
                .antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                .antMatchers(HttpMethod.GET, "/api/usuario").permitAll()                               
                .anyRequest().authenticated();
    }
    private String[] publicEndPoints() {
        return new String[] {  
            "/h2-console/**",
            "/resources/**",
            "/v2/api-docs",
            "/swagger-resources/configuration", 
            "/swagger-resources", 
            "/**.html",
            "/webjars/**", 
            "/login", 
            "/csrf", 
            "/" };
      }
}
