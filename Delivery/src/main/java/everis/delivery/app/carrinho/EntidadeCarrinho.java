package everis.delivery.app.carrinho;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import everis.delivery.app.cliente.EntidadeCliente;
import everis.delivery.app.item.EntidadeItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrinho")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntidadeCarrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "total")
	private double total;

	@Column(name = "criado_as")
	private LocalDateTime criadoAs;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusCarrinho status;

	@OneToOne // OneToOne
	@JoinColumn(name = "cliente_id")
	private EntidadeCliente cliente;

	@OneToMany(mappedBy = "carrinho", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
	private List<EntidadeItem> itens;

	public boolean adicionarItem(EntidadeItem item) {
		return itens.add(item);
	}
	
	public boolean removerItem(EntidadeItem item) {
		return itens.remove(item);
	}

}
