package com.apirest.MedicamentoSolidario.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name="TB_ROLE")
public class Role implements GrantedAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@SequenceGenerators(value = { @SequenceGenerator(name = "id") })
	private long id;
	
	private String nameRole;
	
	@OneToMany(mappedBy = "role")
	private List<Usuario> usuarios;

	public String getNameRole() {
		return nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nameRole;
	}
	
}
