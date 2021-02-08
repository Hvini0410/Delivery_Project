package everis.delivery.app.excessoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ExcessaoClienteNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcessaoClienteNaoEncontrado(String mensagem) {
		super(mensagem);
	}

}
