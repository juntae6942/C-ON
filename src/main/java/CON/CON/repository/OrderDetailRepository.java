package CON.CON.repository;

import CON.CON.model.OrderDetail;
import CON.CON.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderId> {

}
