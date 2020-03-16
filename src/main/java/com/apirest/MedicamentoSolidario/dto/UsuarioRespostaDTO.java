package com.apirest.MedicamentoSolidario.dto;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.apirest.MedicamentoSolidario.Models.Usuario;


public class UsuarioRespostaDTO {
	private long id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date nascimento;
	private String sexo;	
	private String role;

	 private UsuarioRespostaDTO(long id, String nome, String cpf, String email, String telefone, Date nascimento, String sexo, String role) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.sexo = sexo;
		this.role = role;
	}
	 
	public static UsuarioRespostaDTO transformaEmDTO(Usuario usuario) {
		return new UsuarioRespostaDTO(usuario.getId(),usuario.getNome(),usuario.getCpf(),usuario.getEmail(),usuario.getTelefone(),usuario.getNascimento(),usuario.getSexo(), usuario.getRole().getNameRole());
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Date getNascimento() {
		return nascimento;
	}


	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}	
}
