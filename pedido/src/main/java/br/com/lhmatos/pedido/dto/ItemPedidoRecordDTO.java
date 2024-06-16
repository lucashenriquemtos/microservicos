package br.com.lhmatos.pedido.dto;

import br.com.lhmatos.pedido.model.ItemPedido;

import java.io.Serializable;

/**
 * DTO for {@link ItemPedido}
 */
public record ItemPedidoRecordDTO(Long id, int quantidade) implements Serializable {
}