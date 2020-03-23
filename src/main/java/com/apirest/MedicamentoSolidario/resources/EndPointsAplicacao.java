package com.apirest.MedicamentoSolidario.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/")
@Api(value = "API REST doacao")
public class EndPointsAplicacao {
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestHeader Value id){
		
		return null;		
	}
}
