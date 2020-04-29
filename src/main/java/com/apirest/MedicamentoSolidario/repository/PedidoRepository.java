package com.apirest.MedicamentoSolidario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.apirest.MedicamentoSolidario.Models.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{
	Optional<Pedido> findById(long id);
	List<Pedido> findByUsuario(long id);
}
