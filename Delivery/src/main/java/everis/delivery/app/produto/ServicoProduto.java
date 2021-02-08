package everis.delivery.app.produto;

import java.util.List;
import org.springframework.stereotype.Service;

import everis.delivery.app.excessoes.ExcessaoProdutoNaoEncontrado;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoProduto {

	private final RepositorioProduto produtoRepository;

	public List<EntidadeProduto> lista() {
		return produtoRepository.findAll();

	}

	public EntidadeProduto criar(EntidadeProduto produto) {
		return produtoRepository.save(produto);
	}

	public EntidadeProduto findById(Long id) throws ExcessaoProdutoNaoEncontrado {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ExcessaoProdutoNaoEncontrado(String.format("Produto #%d não encontrado", id)));
	}

	public String deletar(Long id) throws ExcessaoProdutoNaoEncontrado {
		EntidadeProduto produto = findById(id);
		produtoRepository.delete(produto);
		return ("Produto deletado com sucesso");
	}

	public EntidadeProduto modificar(Long id, EntidadeProduto produtojson) throws ExcessaoProdutoNaoEncontrado {
		EntidadeProduto produto = findById(id);
		produto.setDescricao(produtojson.getDescricao());
		produto.setPreco(produtojson.getPreco());
		return produtoRepository.save(produto);
	}

}
