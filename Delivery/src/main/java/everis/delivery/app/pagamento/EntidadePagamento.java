package everis.delivery.app.pagamento;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import everis.delivery.app.carrinho.EntidadeCarrinho;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntidadePagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data")
	private LocalDateTime data;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoPagamento tipo;

	@OneToOne(cascade = {CascadeType.MERGE})
	@JoinColumn(name = "carrinho_id")
	private EntidadeCarrinho carrinho;

}
