package br.com.lhmatos.pedido.repository;

import br.com.lhmatos.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}