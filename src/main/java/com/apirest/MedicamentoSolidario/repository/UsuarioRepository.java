package com.apirest.MedicamentoSolidario.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.apirest.MedicamentoSolidario.Models.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long>{
	Optional<Usuario> findById(long id);
	Usuario findByCpf(String cpf);
}
