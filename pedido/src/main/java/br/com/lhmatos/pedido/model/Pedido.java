package br.com.lhmatos.pedido.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "pedido")
@AllArgsConstructor
@Builder
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens;

	@Column(name = "id_cliente", nullable = false)
	private Long idCliente;

	@Column(name = "data_pedido", nullable = false)
	private LocalDate dataPedido;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private StatusPedido status;
}
