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

import everis.delivery.app.carrinho.EntidadeCarrinho;
import everis.delivery.app.carrinho.ServicoCarrinho;
import everis.delivery.app.carrinho.dto.CarrinhoDto;
import everis.delivery.app.excessoes.ExcessaoCarrinhoNaoEncontrado;
import everis.delivery.app.excessoes.ExcessaoClienteNaoEncontrado;
import everis.delivery.app.item.EntidadeItem;
import everis.delivery.app.item.ServicoItem;
import everis.delivery.app.item.dto.ItemDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {

	private final ServicoCarrinho carrinhoService;
	private final ServicoItem itemService;

	@GetMapping
	public List<EntidadeCarrinho> lista() {
		return carrinhoService.lista();
	}

	@GetMapping("/itens")
	public List<EntidadeItem> listagem() {
		return itemService.lista();
	}

	@GetMapping("/{id}")
	public EntidadeCarrinho procurar(@PathVariable Long id) throws ExcessaoCarrinhoNaoEncontrado {
		return carrinhoService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntidadeCarrinho criar(@RequestBody CarrinhoDto dto) throws ExcessaoClienteNaoEncontrado {
		return carrinhoService.criar(dto);
	}

	@PutMapping("/{id}")
	public EntidadeCarrinho atualizar(@PathVariable Long id, @RequestBody EntidadeCarrinho carrinho)
			throws ExcessaoCarrinhoNaoEncontrado {
		return carrinhoService.modificar(id, carrinho);
	}

	@PutMapping("/{id}/adicionar")
	public EntidadeCarrinho adicionar(@PathVariable Long id, @RequestBody List<ItemDto> dto)
			throws ExcessaoCarrinhoNaoEncontrado {
		return carrinhoService.adicionarProdutoCarrinho(id, dto);

	}

	@PutMapping("/{id}/remover")
	public String remover(@PathVariable Long id, @RequestBody List<Long> idItem) throws ExcessaoCarrinhoNaoEncontrado {
		return carrinhoService.removerProdutoCarrinho(id, idItem);

	}

	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) throws ExcessaoCarrinhoNaoEncontrado {
		return carrinhoService.deletar(id);
	}

}
