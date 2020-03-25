package com.apirest.MedicamentoSolidario.resources;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

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
	
	
}
