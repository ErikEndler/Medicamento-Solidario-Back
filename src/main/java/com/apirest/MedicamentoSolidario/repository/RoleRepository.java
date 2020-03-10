package com.apirest.MedicamentoSolidario.repository;

import org.springframework.data.repository.CrudRepository;

import com.apirest.MedicamentoSolidario.Models.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByNameRole(String role);

}
