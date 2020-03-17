package com.apirest.MedicamentoSolidario.security;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
    	http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
    	//http.formLogin().permitAll();
        http.authorizeRequests()
        		.antMatchers(publicEndPoints()).permitAll() 
                .antMatchers(HttpMethod.POST, "/api/usuario").permitAll()
                .antMatchers(HttpMethod.GET,  "/api/usuario").permitAll()                               
                .anyRequest().authenticated();
//        http.sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
    }
    private String[] publicEndPoints() {
        return new String[] {  "/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**",
        		 "/error/**",
        "/h2-console/**", "/resources/**", "/v2/rest-docs", "/v2/api-docs",
        "/swagger-resources/configuration", "/swagger-resources", "/**.html",
        "/webjars/**", "/login", "/csrf", "/" };
      }
//    
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() 
//    {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
}
