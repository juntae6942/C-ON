package CON.CON.repository;

import CON.CON.model.ContainId;
import CON.CON.model.Contains;
import CON.CON.model.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContainsRepository extends JpaRepository<Contains, ContainId> {
    @Query("SELECT c.foodName FROM Contains c WHERE c.categoryName.categoryName = :categoryName")
    List<Food> findByCategoryName(@Param("categoryName") String categoryName);
}
