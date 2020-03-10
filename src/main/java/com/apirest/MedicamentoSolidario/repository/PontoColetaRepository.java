package com.apirest.MedicamentoSolidario.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.apirest.MedicamentoSolidario.Models.PontoColeta;

public interface PontoColetaRepository extends CrudRepository<PontoColeta, Long> {
	Optional<PontoColeta> findById(long id);
}
