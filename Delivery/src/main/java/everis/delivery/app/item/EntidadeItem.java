package everis.delivery.app.item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import everis.delivery.app.carrinho.EntidadeCarrinho;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntidadeItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "produto")
	private String produto;

	@Column(name = "preco")
	private double preco;

	@Column(name = "quantidade")
	private int quantidade;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "carrinho_id")
	@JsonIgnore
	private EntidadeCarrinho carrinho;
}
