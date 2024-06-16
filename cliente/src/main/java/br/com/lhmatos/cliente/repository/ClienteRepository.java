package br.com.lhmatos.cliente.repository;

import br.com.lhmatos.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
