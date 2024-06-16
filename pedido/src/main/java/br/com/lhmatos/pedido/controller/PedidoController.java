package br.com.lhmatos.pedido.controller;

import br.com.lhmatos.pedido.dto.PedidoRecordDTO;
import br.com.lhmatos.pedido.service.PedidoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

	@Autowired
	private PedidoService pedidoService;

	@PostMapping
	public ResponseEntity<PedidoRecordDTO> criarPedido(@RequestBody @Valid PedidoRecordDTO pedido) {
		try {
			PedidoRecordDTO pedidoCriado = pedidoService.criarPedido(pedido);
			return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<PedidoRecordDTO> buscarPedidoPorId(@PathVariable Long id) {
		logger.info("Recebida solicitação para buscar pedido com ID: {}", id);
		try {
			PedidoRecordDTO pedido = pedidoService.buscarPedidoPorId(id);
			return ResponseEntity.ok(pedido);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
