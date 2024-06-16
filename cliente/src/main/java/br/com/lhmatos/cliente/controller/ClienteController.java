package br.com.lhmatos.cliente.controller;

import br.com.lhmatos.cliente.dto.ClienteRecordDTO;
import br.com.lhmatos.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<ClienteRecordDTO> salvarUsuario(@RequestBody @Valid ClienteRecordDTO clienteRecordDTO) {
		try {
			ClienteRecordDTO clienteSalvo = clienteService.salvarCliente(clienteRecordDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
		} catch (Exception e) {
			logger.error("Erro ao salvar o cliente: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteRecordDTO> buscarClientePorId(@PathVariable Long id) {
		try {
			ClienteRecordDTO cliente = clienteService.buscarClientePorId(id);
			if (cliente != null) {
				logger.info("Cliente encontrado com sucesso: {}", cliente);
				return ResponseEntity.ok(cliente);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			logger.error("Erro ao buscar o cliente com ID {}: {}", id, e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}



}
