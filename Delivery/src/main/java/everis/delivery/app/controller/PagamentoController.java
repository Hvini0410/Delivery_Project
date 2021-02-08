package everis.delivery.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import everis.delivery.app.excessoes.ExcessaoCarrinhoNaoEncontrado;
import everis.delivery.app.excessoes.ExcessaoPagamentoNaoEncontrado;
import everis.delivery.app.pagamento.EntidadePagamento;
import everis.delivery.app.pagamento.ServicoPagamento;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

	private final ServicoPagamento pagamentoService;

	@GetMapping
	public List<EntidadePagamento> lista() {
		return pagamentoService.lista();
	}

	@GetMapping("/{id}")
	public EntidadePagamento procurar(@PathVariable Long id) {
		return pagamentoService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntidadePagamento criar(@RequestBody EntidadePagamento pagamento) throws ExcessaoCarrinhoNaoEncontrado {
		return pagamentoService.criar(pagamento);

	}

	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws ExcessaoPagamentoNaoEncontrado {
		return pagamentoService.deletar(id);
	}

}
