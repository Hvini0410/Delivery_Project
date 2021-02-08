package everis.delivery.app.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioItem extends JpaRepository<EntidadeItem, Long>{

}
