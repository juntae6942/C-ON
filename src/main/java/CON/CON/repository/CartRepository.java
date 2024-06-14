package CON.CON.repository;

import CON.CON.model.Cart;
import CON.CON.model.Customer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByCno(Customer cno);
    List<Cart> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
