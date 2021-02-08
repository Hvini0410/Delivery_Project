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

import everis.delivery.app.excessoes.ExcessaoProdutoNaoEncontrado;
import everis.delivery.app.produto.EntidadeProduto;
import everis.delivery.app.produto.ServicoProduto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

	private final ServicoProduto produtoService;

	@GetMapping
	public List<EntidadeProduto> lista() {
		return produtoService.lista();
	}

	@GetMapping("/{id}")
	public EntidadeProduto procurar(@PathVariable Long id) throws ExcessaoProdutoNaoEncontrado {
		return produtoService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntidadeProduto criar(@RequestBody EntidadeProduto produto) {
		return produtoService.criar(produto);
	}

	@PutMapping("/{id}")
	public EntidadeProduto modificar(@PathVariable Long id, @RequestBody EntidadeProduto produto)
			throws ExcessaoProdutoNaoEncontrado {
		return produtoService.modificar(id, produto);
	}

	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws ExcessaoProdutoNaoEncontrado {
		return produtoService.deletar(id);
	}
}
