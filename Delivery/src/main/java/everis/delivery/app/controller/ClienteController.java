package everis.delivery.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import everis.delivery.app.cliente.EntidadeCliente;
import everis.delivery.app.cliente.ServicoCliente;
import everis.delivery.app.excessoes.ExcessaoClienteNaoEncontrado;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

	private final ServicoCliente clienteService;

	@GetMapping
	public List<EntidadeCliente> list() {
		return clienteService.lista();
	}

	@GetMapping("{id}")
	public EntidadeCliente procurar(@PathVariable Long id) throws ExcessaoClienteNaoEncontrado {
		return clienteService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntidadeCliente create(@RequestBody EntidadeCliente cliente) {
		return clienteService.criar(cliente);
	}

	@PutMapping("{id}")
	public EntidadeCliente modificar(@PathVariable Long id, @RequestBody EntidadeCliente cliente)
			throws ExcessaoClienteNaoEncontrado {
		return clienteService.modificar(id, cliente);
	}

	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws ExcessaoClienteNaoEncontrado {
		return clienteService.deletar(id);
	}

}
