package br.com.lhmatos.pedido.dto;

import br.com.lhmatos.pedido.model.Produto;

import java.io.Serializable;

/**
 * DTO for {@link Produto}
 */
public record ProdutoRecordDTO(Long id, String nome, double preco) implements Serializable {
}