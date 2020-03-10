package com.apirest.MedicamentoSolidario.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.apirest.MedicamentoSolidario.Models.Doacao;

public interface DoacaoRepository extends CrudRepository<Doacao, Long> {
	Optional<Doacao> findById(long id);
}
