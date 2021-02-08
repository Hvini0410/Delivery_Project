package everis.delivery.app.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPagamento extends JpaRepository<EntidadePagamento, Long> {

}
