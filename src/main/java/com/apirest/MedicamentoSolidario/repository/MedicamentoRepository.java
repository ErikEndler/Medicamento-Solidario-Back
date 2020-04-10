package com.apirest.MedicamentoSolidario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.apirest.MedicamentoSolidario.Models.Medicamento;

public interface MedicamentoRepository extends PagingAndSortingRepository<Medicamento, Long> {
	Optional<Medicamento> findById(long id);
	@Query(value = "select * from tb_medicamento "
			+ "join tb_doacao "
			+ "on tb_medicamento.doacao_in_id = tb_doacao.id "
			+ "where tb_doacao.ponto_id = ?1", nativeQuery = true)
	List<Medicamento> findMedicamentosByPonto(long ponto);
	
}
