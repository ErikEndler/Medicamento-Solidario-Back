package com.apirest.MedicamentoSolidario.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.apirest.MedicamentoSolidario.Models.Medicamento;

public interface MedicamentoRepository extends PagingAndSortingRepository<Medicamento, Long> {
	Optional<Medicamento> findById(long id);
	
}
