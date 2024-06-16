package br.com.lhmatos.pedido.service;

import br.com.lhmatos.pedido.api.ClienteRecordDTO;
import br.com.lhmatos.pedido.client.ClienteFeignClient;
import br.com.lhmatos.pedido.dto.PedidoRecordDTO;
import br.com.lhmatos.pedido.model.Pedido;
import br.com.lhmatos.pedido.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PedidoService {

	private static final Logger logger = LoggerFactory.getLogger(PedidoService.class);

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ClienteFeignClient clienteFeignClient;

	@Transactional
	public PedidoRecordDTO criarPedido(PedidoRecordDTO pedidoDTO) {

		Pedido pedido = objectMapper.convertValue(pedidoDTO, Pedido.class);

		ClienteRecordDTO cliente = clienteFeignClient.buscarClientePorId(pedido.getIdCliente()).getBody();
		if (cliente == null) {
			String mensagem = "Cliente não encontrado com o ID: " + pedido.getIdCliente();
			logger.warn(mensagem);
			throw new NoSuchElementException(mensagem);
		}

		pedido = pedidoRepository.save(pedido);

		return objectMapper.convertValue(pedido, PedidoRecordDTO.class);
	}

	public PedidoRecordDTO buscarPedidoPorId(Long id) {
		logger.info("Buscando pedido com ID: {}", id);

		Pedido pedido = pedidoRepository.findById(id)
				.orElseThrow(() -> {
					logger.error("Pedido não encontrado com o ID: {}", id);
					return new NoSuchElementException("Pedido não encontrado com o ID: " + id);
				});

		logger.info("Pedido encontrado com sucesso: {}", pedido);

		ClienteRecordDTO cliente = clienteFeignClient.buscarClientePorId(pedido.getIdCliente()).getBody();

		if (cliente == null) {
			logger.warn("Cliente não encontrado com o ID associado ao pedido: {}", pedido.getIdCliente());
			throw new NoSuchElementException("Cliente não encontrado com o ID associado ao pedido: " + pedido.getIdCliente());
		}

		logger.info("Cliente encontrado com sucesso: {}", cliente);

		return new PedidoRecordDTO(pedido.getId(), pedido.getDataPedido(), pedido.getStatus(), cliente);
	}
}