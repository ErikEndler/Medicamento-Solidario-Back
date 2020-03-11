package com.apirest.MedicamentoSolidario.dto;

import java.sql.Date;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.apirest.MedicamentoSolidario.Models.Role;
import com.apirest.MedicamentoSolidario.Models.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioDTO implements UserDetails {
	private long id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	private Date nascimento;
	private String senha;
	private String sexo;
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
	
	public UsuarioDTO(Usuario usuario) {
	    this.cpf = usuario.getCpf();
	    this.senha = usuario.getSenha();
	}
//-------------------------------------
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nome;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
