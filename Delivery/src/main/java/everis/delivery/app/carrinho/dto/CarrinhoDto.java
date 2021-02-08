package everis.delivery.app.carrinho.dto;

import java.util.List;

import everis.delivery.app.item.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarrinhoDto {
	
	private Long clienteId;
	private List<ItemDto> produtos;

}
