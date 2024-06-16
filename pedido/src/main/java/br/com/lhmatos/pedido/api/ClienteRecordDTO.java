package br.com.lhmatos.pedido.api;


import jakarta.validation.constraints.Email;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Cliente}
 */
public record ClienteRecordDTO(Long id, String nome, String cpf, @Email String email, String telefone, String endereco,
							   LocalDate dataDeNascimento, LocalDate dataDeCadastro, String genero,
							   String status) implements Serializable {
}