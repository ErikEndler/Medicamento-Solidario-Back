package com.apirest.MedicamentoSolidario.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.apirest.MedicamentoSolidario.Models.Role;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioDTO {
	private long id;
	private String nome;
	@NotBlank
	private String cpf;
	@Email
	private String email;
	private String telefone;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	private Date nascimento;
	@NotBlank
	private String senha;
	private String sexo;
	@NotBlank
	private String role;
	@JsonIgnore
	private Role fullRole;
	

	public Usuario trsnformaParaObjSalvar() {
		return new Usuario(nome, cpf, email, telefone, nascimento, senha, sexo, fullRole);
	}

	public Usuario trsnformaParaObjEditar() {
		return new Usuario(id, nome, cpf, email, telefone, nascimento, senha, sexo, fullRole);
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

}
