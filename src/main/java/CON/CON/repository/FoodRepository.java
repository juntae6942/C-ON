package CON.CON.repository;

import CON.CON.model.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, String> {
    @Query("SELECT m FROM Food m WHERE m.price BETWEEN :minPrice AND :maxPrice AND m.foodName LIKE %:keyword%")
    List<Food> findMenusByPriceRangeAndKeyword(@Param("minPrice") int minPrice,
                                               @Param("maxPrice") int maxPrice,
                                               @Param("keyword") String keyword);
}
