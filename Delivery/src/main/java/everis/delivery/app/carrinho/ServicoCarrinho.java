package everis.delivery.app.carrinho;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import everis.delivery.app.carrinho.dto.CarrinhoDto;
import everis.delivery.app.cliente.EntidadeCliente;
import everis.delivery.app.cliente.ServicoCliente;
import everis.delivery.app.excessoes.ExcessaoCarrinhoNaoEncontrado;
import everis.delivery.app.excessoes.ExcessaoClienteNaoEncontrado;
import everis.delivery.app.item.EntidadeItem;
import everis.delivery.app.item.RepositorioItem;
import everis.delivery.app.item.ServicoItem;
import everis.delivery.app.item.dto.ItemDto;
import everis.delivery.app.produto.EntidadeProduto;
import everis.delivery.app.produto.ServicoProduto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoCarrinho {

	private final RepositorioCarrinho carrinhoRepository;
	private final ServicoCliente clienteService;
	private final ServicoProduto produtoService;
	private final ServicoItem itemService;
	private final RepositorioItem itemRepository;

	public List<EntidadeCarrinho> lista() {
		return carrinhoRepository.findAll();
	}

	public EntidadeCarrinho criar(CarrinhoDto dto) throws ExcessaoClienteNaoEncontrado {
		EntidadeCliente cliente = clienteService.findById(dto.getClienteId());
		EntidadeCarrinho carrinho = EntidadeCarrinho.builder().cliente(cliente).criadoAs(LocalDateTime.now())
				.status(StatusCarrinho.PENDENTE).build();

		List<EntidadeItem> itens = dto.getProdutos().stream().map(item -> {
			try {
				EntidadeProduto produto = produtoService.findById(item.getProdutoId());
				return EntidadeItem.builder().carrinho(carrinho).produto(produto.getDescricao())
						.preco(produto.getPreco()).quantidade(item.getQuantidade()).build();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}).collect(Collectors.toList());

		double total = itens.stream().filter(item -> item.getQuantidade() > 0)
				.mapToDouble(item -> item.getPreco() * item.getQuantidade()).sum();

		carrinho.setTotal(total);
		carrinho.setItens(itens);

		return carrinhoRepository.save(carrinho);
	}

	public EntidadeCarrinho findById(Long id) throws ExcessaoCarrinhoNaoEncontrado {
		return carrinhoRepository.findById(id)
				.orElseThrow(() -> new ExcessaoCarrinhoNaoEncontrado("Carrinho não encontrado"));
	}

	public String deletar(Long id) throws ExcessaoCarrinhoNaoEncontrado {
		EntidadeCarrinho carrinho = findById(id);
		carrinhoRepository.delete(carrinho);
		return ("Carrinho deletado com sucesso");
	}

	public EntidadeCarrinho modificar(Long id, EntidadeCarrinho carrinhojson) throws ExcessaoCarrinhoNaoEncontrado {
		EntidadeCarrinho carrinho = findById(id);
		carrinho.setCliente(carrinhojson.getCliente());
		List<EntidadeItem> itens = carrinhojson.getItens();
		carrinho.setItens(itens);
		double total = itens.stream().filter(item -> item.getQuantidade() > 0)
				.mapToDouble(item -> item.getPreco() * item.getQuantidade()).sum();
		carrinho.setTotal(total);
		return carrinhoRepository.save(carrinho);
	}

	public EntidadeCarrinho adicionarProdutoCarrinho(Long idCarrinho, List<ItemDto> produtosjson) {
		EntidadeCarrinho carrinho = findById(idCarrinho);
		produtosjson.forEach(info -> {
			EntidadeProduto produto = produtoService.findById(info.getProdutoId());
			EntidadeItem item = EntidadeItem.builder().carrinho(carrinho).produto(produto.getDescricao())
					.quantidade(info.getQuantidade()).preco(produto.getPreco()).build();
			carrinho.adicionarItem(item);
		});
		Double total = carrinho.getItens().stream().filter(item -> item.getQuantidade() > 0)
				.mapToDouble(item -> item.getPreco() * item.getQuantidade()).sum();
		carrinho.setTotal(total);
		return carrinhoRepository.save(carrinho);
	}

	public String removerProdutoCarrinho(Long idCarrinho, List<Long> idItem) {
		EntidadeCarrinho carrinho = findById(idCarrinho);
		idItem.forEach(id -> {
			EntidadeItem item = itemService.findById(id);
			carrinho.removerItem(item);
			itemRepository.deleteById(id);
			
		});
		Double total = carrinho.getItens().stream().filter(item -> item.getQuantidade() > 0)
				.mapToDouble(item -> item.getPreco() * item.getQuantidade()).sum();
		carrinho.setTotal(total);
		carrinhoRepository.save(carrinho);
		return ("Produtos retirados com sucesso");

	}
}
