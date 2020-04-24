package com.apirest.MedicamentoSolidario.dto;

import java.time.LocalDate;

import com.apirest.MedicamentoSolidario.Models.Usuario;

public class UsuarioRespostaDTO {
	private long id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private LocalDate dataNascimento;
	private String sexo;
	private String role;

	private UsuarioRespostaDTO(long id, String nome, String cpf, String email, String telefone, LocalDate date,
			String sexo, String role) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.setDataNascimento(date);
		this.sexo = sexo;
		this.role = role;
	}

	public static UsuarioRespostaDTO transformaEmDTO(Usuario usuario) {
		return new UsuarioRespostaDTO(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(),
				usuario.getTelefone(), usuario.getNascimento(), usuario.getSexo(), usuario.getRole().getNameRole());
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
