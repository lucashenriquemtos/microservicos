package br.com.lhmatos.pedido.dto;

import br.com.lhmatos.pedido.api.ClienteRecordDTO;
import br.com.lhmatos.pedido.model.Pedido;
import br.com.lhmatos.pedido.model.StatusPedido;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Pedido}
 */
public record PedidoRecordDTO(Long id, LocalDate dataPedido, StatusPedido status, ClienteRecordDTO cliente) implements Serializable {
}