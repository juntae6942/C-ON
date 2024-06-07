package CON.CON.service;

import CON.CON.dto.FoodInfo;
import CON.CON.model.Contains;
import CON.CON.repository.ContainsRepository;
import CON.CON.repository.FoodRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
    private final FoodRepository repository;
    private final ContainsRepository contains;

    public FoodService(FoodRepository repository, ContainsRepository contains) {
        this.repository = repository;
        this.contains = contains;
    }

    public List<FoodInfo> findFoods() {
        return repository.findAll().stream()
                .map( food -> new FoodInfo(food.getFoodName(), food.getPrice())).toList();
    }

    public List<FoodInfo> findByCategory(String categoryName) {
        return contains.findByCategoryName(categoryName).stream()
                .map(food -> new FoodInfo(food.getFoodName(), food.getPrice())).toList();
    }

    public List<FoodInfo> findByPriceAndWord(int minPrice, int maxPrice, String keyWord) {
        return repository.findMenusByPriceRangeAndKeyword(minPrice, maxPrice, keyWord).stream()
                .map(food -> new FoodInfo(food.getFoodName(), food.getPrice())).toList();
    }
}
