package CON.CON.repository;

import CON.CON.dto.CustomerSalesRank;
import CON.CON.model.Cart;
import CON.CON.model.OrderDetail;
import CON.CON.model.OrderId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderId> {
    List<OrderDetail> findAllById(Cart cart);

    @Query("SELECT c.cno, SUM(od.totalPrice)" +
            "FROM Cart c JOIN  OrderDetail od " +
            "ON c.id = od.id.id " +
            "WHERE FUNCTION('MONTH', c.dateTime) = FUNCTION('MONTH', CURRENT_DATE) " +
            "AND FUNCTION('YEAR', c.dateTime) = FUNCTION('YEAR', CURRENT_DATE) " +
            "GROUP BY c.cno " +
            "ORDER BY 2 desc "
    )
    List<Object[]> findCustomerOrderTotalForCurrentMonth();

    @Query("SELECT c.cno, RANK() OVER(ORDER BY SUM(od.totalPrice) DESC)" +
            "FROM Cart c JOIN OrderDetail od " +
            "ON c.id = od.id.id " +
            "GROUP BY c.cno")
    List<Object[]> getSalesRanking();
}
