package everis.delivery.app.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEndereco extends JpaRepository<EntidadeEndereco, Long> {

}
