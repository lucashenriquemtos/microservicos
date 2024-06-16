package br.com.lhmatos.cliente.service;


import br.com.lhmatos.cliente.dto.ClienteRecordDTO;
import br.com.lhmatos.cliente.model.Cliente;
import br.com.lhmatos.cliente.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Transactional
	public ClienteRecordDTO salvarCliente(ClienteRecordDTO clienteDTO) {
		var cliente = objectMapper.convertValue(clienteDTO, Cliente.class);
		cliente = clienteRepository.save(cliente);
		return objectMapper.convertValue(cliente, ClienteRecordDTO.class);
	}

	public ClienteRecordDTO buscarClientePorId(Long id) {
		var cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Cliente não encontrado com o ID: " + id));
		return objectMapper.convertValue(cliente, ClienteRecordDTO.class);
	}
}
