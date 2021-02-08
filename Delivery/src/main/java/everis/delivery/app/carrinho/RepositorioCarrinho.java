package everis.delivery.app.carrinho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCarrinho extends JpaRepository<EntidadeCarrinho, Long>{

}
