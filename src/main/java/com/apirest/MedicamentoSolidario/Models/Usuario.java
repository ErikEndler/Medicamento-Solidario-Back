package com.apirest.MedicamentoSolidario.Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Entity
@Table(name="TB_USUARIO", uniqueConstraints={@UniqueConstraint(columnNames={"cpf"})})

public class Usuario implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable=false, updatable=false)
	private long id;
	private String nome;
	@NotBlank
	private String cpf;
	@Email
	private String email;
	private String telefone;	
	private LocalDate nascimento;
	@NotBlank
	private String senha;
	private String sexo;	
	
	@ManyToOne
	private Role role;
//	@ManyToMany
//	@JoinTable(name = "usuario_role",
//	joinColumns = @JoinColumn(name="usuario_id", referencedColumnName = "id"),
//	inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id")
//			)
//    private List<Role> roles;
	
	@OneToMany(mappedBy = "doador")
	private List<Doacao> medicamentoDoado;
	@OneToMany(mappedBy = "voluntario")
	private List<Doacao> medicamentoIntermediado;
	@OneToMany(mappedBy = "voluntario")
	private List<Recebimento> medicamentoIntermediado_out;
	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos;
	
	public Usuario() {
		super();
	}

	public Usuario(String nome, String cpf, String email, String telefone, LocalDate nascimento2,String senha, String sexo, Role role) {
		this.nome=nome;
		this.cpf=cpf;
		this.email=email;
		this.telefone=telefone;
		this.nascimento=nascimento2;		
		this.senha=senha;
		this.sexo=sexo;
		this.role=role;		
	}
	
	public Usuario(long id, String nome, String cpf, String email, String telefone, LocalDate nascimento,
			String senha, String sexo, Role role) {
		this.id=id;
		this.nome=nome;
		this.cpf=cpf;
		this.email=email;
		this.telefone=telefone;
		this.nascimento=nascimento;		
		this.senha=senha;
		this.sexo= sexo;
		this.role= role;
	}	

	public Role getRole() {
		return role;
	}

	public void setRole(Role roles) {
		this.role = roles;
	}
	public List<Doacao> getMedicamentoDoado() {
		return medicamentoDoado;
	}
	public void setMedicamentoDoado(List<Doacao> medicamentoDoado) {
		this.medicamentoDoado = medicamentoDoado;
	}
	public List<Doacao> getMedicamentoIntermediado() {
		return medicamentoIntermediado;
	}
	public void setMedicamentoIntermediado(List<Doacao> medicamentoIntermediado) {
		this.medicamentoIntermediado = medicamentoIntermediado;
	}
	public List<Recebimento> getMedicamentoIntermediado_out() {
		return medicamentoIntermediado_out;
	}
	public void setMedicamentoIntermediado_out(List<Recebimento> medicamentoIntermediado_out) {
		this.medicamentoIntermediado_out = medicamentoIntermediado_out;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

//------------------------USERDETAILS METODOS-----------------
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<Role> RRoles = new ArrayList<Role>();
		RRoles.add(role);
		return  RRoles;
	}
		
	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.cpf;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}	
}
