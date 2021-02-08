package everis.delivery.app.item;

import java.util.List;

import org.springframework.stereotype.Service;

import everis.delivery.app.excessoes.ExcessaoItemNaoEncontrado;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoItem {

	private final RepositorioItem itemRepository;
	
	public List<EntidadeItem> lista() {
		return itemRepository.findAll();
	}

	public EntidadeItem findById(Long id) {
		return itemRepository.findById(id).orElseThrow(() -> new ExcessaoItemNaoEncontrado("Item nãoencontrado"));
	}

}
