package CON.CON.repository;

import CON.CON.model.ContainId;
import CON.CON.model.Contains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainsRepository extends JpaRepository<Contains, ContainId> {
}
