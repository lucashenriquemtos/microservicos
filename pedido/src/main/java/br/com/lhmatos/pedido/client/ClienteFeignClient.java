package br.com.lhmatos.pedido.client;

import br.com.lhmatos.pedido.api.ClienteRecordDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente", url = "http://localhost:8081")
public interface ClienteFeignClient {

	@GetMapping("/clientes/{id}")
	ResponseEntity<ClienteRecordDTO> buscarClientePorId(@PathVariable Long id);
}
