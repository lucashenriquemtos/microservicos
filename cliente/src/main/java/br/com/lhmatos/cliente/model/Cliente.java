package br.com.lhmatos.cliente.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
	private String cpf;

	@Email
	private String email;

	@Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve seguir o padrão (xx) xxxx-xxxx ou (xx) xxxxx-xxxx")
	private String telefone;

	private String endereco;

	@PastOrPresent(message = "Data de nascimento deve ser uma data passada ou presente")
	private LocalDate dataDeNascimento;

	@PastOrPresent(message = "Data de cadastro deve ser uma data passada ou presente")
	private LocalDate dataDeCadastro;

	private String genero;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status não pode ser nulo")
	private StatusCliente status;

	@PrePersist
	protected void onCreate() {
		if (this.dataDeCadastro == null) {
			this.dataDeCadastro = LocalDate.now();
		}
		if (this.status == null) {
			this.status = StatusCliente.ATIVO;
		}
	}

}
