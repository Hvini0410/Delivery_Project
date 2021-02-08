package everis.delivery.app.pagamento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import everis.delivery.app.carrinho.EntidadeCarrinho;
import everis.delivery.app.carrinho.ServicoCarrinho;
import everis.delivery.app.carrinho.StatusCarrinho;
import everis.delivery.app.excessoes.ExcessaoCarrinhoNaoEncontrado;
import everis.delivery.app.excessoes.ExcessaoPagamentoNaoEncontrado;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoPagamento {

	private final RepositorioPagamento pagamentoRepository;
	private final ServicoCarrinho carrinhoService;

	public List<EntidadePagamento> lista() {
		return pagamentoRepository.findAll();
	}

	public EntidadePagamento criar(EntidadePagamento pagamento) throws ExcessaoCarrinhoNaoEncontrado {
		EntidadeCarrinho carrinho = carrinhoService.findById(pagamento.getCarrinho().getId());
		carrinho.setStatus(StatusCarrinho.CONCLUIDO);

		pagamento.setData(LocalDateTime.now());
		pagamento.setCarrinho(carrinho);
		return pagamentoRepository.save(pagamento);
	}

	public EntidadePagamento findById(Long id) throws ExcessaoPagamentoNaoEncontrado {
		return pagamentoRepository.findById(id)
				.orElseThrow(() -> new ExcessaoPagamentoNaoEncontrado("Pagamento não encontrado"));

	}

	public String deletar(Long id) throws ExcessaoPagamentoNaoEncontrado {
		EntidadePagamento pagamento = findById(id);
		pagamentoRepository.delete(pagamento);
		return ("Pagamento deletado com sucesso");
	}

}
