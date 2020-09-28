package com.apirest.MedicamentoSolidario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.apirest.MedicamentoSolidario.Models.Pedido;
import com.apirest.MedicamentoSolidario.Models.PontoColeta;
import com.apirest.MedicamentoSolidario.Models.Usuario;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
	Optional<Pedido> findById(long id);

	List<Pedido> findByUsuario(Usuario usuario);
	List<Pedido> findByPonto(PontoColeta ponto);

}
