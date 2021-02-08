package everis.delivery.app.cliente;

import java.util.List;

import org.springframework.stereotype.Service;

import everis.delivery.app.excessoes.ExcessaoClienteNaoEncontrado;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoCliente {

	private final RepositorioCliente clienteRepository;

	public List<EntidadeCliente> lista() {
		return clienteRepository.findAll();
	}

	public EntidadeCliente criar(EntidadeCliente cliente) {
		return clienteRepository.save(cliente);
	}

	public EntidadeCliente findById(Long id) throws ExcessaoClienteNaoEncontrado {
		return clienteRepository.findById(id).orElseThrow(
				() -> new ExcessaoClienteNaoEncontrado("Cliente não encotrado, faça o cadastro antes, por favor"));
	}
	
	public String deletar(Long id) throws ExcessaoClienteNaoEncontrado {
		EntidadeCliente cliente = findById(id);
		clienteRepository.delete(cliente);
		return ("Cliente deletado com sucesso");
	}
	
	public EntidadeCliente modificar(Long id, EntidadeCliente clientejson) throws ExcessaoClienteNaoEncontrado {
		EntidadeCliente cliente = findById(id);
		cliente.setNome(clientejson.getNome());
		cliente.setCpf(clientejson.getCpf());
		cliente.setTelefone(clientejson.getTelefone());
		cliente.setEmail(clientejson.getEmail());
		cliente.setEndereco(clientejson.getEndereco());
		return clienteRepository.save(cliente);
	}
}
