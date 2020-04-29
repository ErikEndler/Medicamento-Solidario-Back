package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.apirest.MedicamentoSolidario.Models.Role;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioDTO {
	private long id;
	private String nome;
	@NotBlank
	private String cpf;
	@Email
	private String email;
	private String telefone;
	@NotBlank
	private String dataNascimento;	
	@NotBlank
	private String senha;
	private String sexo;
	@NotBlank
	private String role;
	
	@JsonIgnore
	private LocalDate nascimento;
	@JsonIgnore
	private Role fullRole;
	
	//transforma a senha string recebida pelo jason em formato LocalDate
	private LocalDate converterData() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		this.nascimento = LocalDate.parse(this.dataNascimento, formato); 
		return this.nascimento ;
	}

	public Usuario trsnformaParaObjSalvar() {
		return new Usuario(nome, cpf, email, telefone, converterData(), senha, sexo, fullRole);
	}

	public Usuario trsnformaParaObjEditar() {
		return new Usuario(id, nome, cpf, email, telefone, converterData(), senha, sexo, fullRole);
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

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Role getFullRole() {
		return fullRole;
	}

	public void setFullRole(Role fullRole) {
		this.fullRole = fullRole;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
