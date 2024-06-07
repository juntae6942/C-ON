package CON.CON.init;

import CON.CON.dto.OrderInfo;
import CON.CON.model.Category;
import CON.CON.model.Contains;
import CON.CON.model.Customer;
import CON.CON.model.Food;
import CON.CON.repository.CategoryRepository;
import CON.CON.repository.ContainsRepository;
import CON.CON.repository.CustomerRepository;
import CON.CON.repository.FoodRepository;
import CON.CON.service.OrderService;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class InitFoodsData {

    private final FoodRepository foodRepository;
    private final CategoryRepository categoryRepository;
    private final ContainsRepository containsRepository;
    private final CustomerRepository customerRepository;
    private final OrderService orderService;

    public InitFoodsData(FoodRepository foodRepository, CategoryRepository categoryRepository,
                         ContainsRepository containsRepository, CustomerRepository customerRepository,
                         OrderService orderService) {
        this.foodRepository = foodRepository;
        this.categoryRepository = categoryRepository;
        this.containsRepository = containsRepository;
        this.customerRepository = customerRepository;
        this.orderService = orderService;
    }

    @PostConstruct
    public void initCustomer() {
        customerRepository.save(Customer.builder()
                        .cno("c0")
                        .name("test")
                        .password("test")
                        .phoneNumber("010-0000-0000")
                .build());
        customerRepository.save(Customer.builder()
                        .cno("test1")
                        .name("test")
                        .password("test")
                        .phoneNumber("010-0000-0000")
                .build());
        customerRepository.save(Customer.builder()
                .cno("test2")
                .name("test")
                .password("test")
                .phoneNumber("010-0000-0000")
                .build());
        customerRepository.save(Customer.builder()
                .cno("test3")
                .name("test")
                .password("test")
                .phoneNumber("010-0000-0000")
                .build());
    }

    @PostConstruct
    public void initCategory() {
        categoryRepository.save(new Category("초밥", 10));
        categoryRepository.save(new Category("튀김류", 3));
        categoryRepository.save(new Category("음료", 6));
        categoryRepository.save(new Category("면류", 1));
    }

    @PostConstruct
    public void initFood() {
        foodRepository.save(new Food("유부초밥", 2000));
        foodRepository.save(new Food("새우초밥", 2000));
        foodRepository.save(new Food("장어초밥", 3000));
        foodRepository.save(new Food("연어초밥", 3000));
        foodRepository.save(new Food("새우튀김", 3000));
        foodRepository.save(new Food("계란초밥", 2000));
        foodRepository.save(new Food("광어초밥", 3000));
        foodRepository.save(new Food("참치초밥", 4000));
        foodRepository.save(new Food("대게초밥", 3000));
        foodRepository.save(new Food("오징어튀김",3000));
        foodRepository.save(new Food("새우장초밥",3000));
        foodRepository.save(new Food("콜라",2000));
        foodRepository.save(new Food("사이다",2000));
        foodRepository.save(new Food("제로콜라",2000));
        foodRepository.save(new Food("제로사이다",2000));
        foodRepository.save(new Food("맥주",4000));
        foodRepository.save(new Food("소주",4000));
        foodRepository.save(new Food("돈카츠",6000));
        foodRepository.save(new Food("소고기초밥",4000));
        foodRepository.save(new Food("냉모밀",5000));
    }

    @PostConstruct
    public void initContains() {
        List<Food> foods = foodRepository.findAll();

        Optional<Category> sushi = categoryRepository.findById("초밥");
        Optional<Category> fried = categoryRepository.findById("튀김류");
        Optional<Category> noodle = categoryRepository.findById("면류");
        Optional<Category> drink = categoryRepository.findById("음료");

        if (!sushi.isPresent() || !fried.isPresent() || !noodle.isPresent() || !drink.isPresent()) {
            throw new RuntimeException("One or more categories are missing.");
        }

        for (Food food : foods) {
            String foodName = food.getFoodName();

            if (foodName.contains("초밥")) {
                containsRepository.save(new Contains(food, sushi.get()));
            } else if (foodName.contains("튀김")) {
                containsRepository.save(new Contains(food, fried.get()));
            } else if (foodName.equals("냉모밀")) {
                containsRepository.save(new Contains(food, noodle.get()));
            } else if (foodName.equals("돈카츠")) {
                containsRepository.save(new Contains(food, fried.get()));
            } else {
                containsRepository.save(new Contains(food, drink.get()));
            }
        }
    }

    @PostConstruct
    public void initOrder() {

        orderService.order("test1",List.of(OrderInfo.builder()
                        .foodName("유부초밥")
                        .price(2000)
                        .quantity(5)
                .build()));

        orderService.order("test1",List.of(OrderInfo.builder()
                .foodName("새우초밥")
                .price(2000)
                .quantity(5)
                .build()));

        orderService.order("test2",List.of(OrderInfo.builder()
                        .foodName("연어초밥")
                        .price(3000)
                        .quantity(3)
                .build()));

        orderService.order("test2",List.of(OrderInfo.builder()
                .foodName("대게초밥")
                .price(3000)
                .quantity(3)
                .build()));

        orderService.order("test3",List.of(OrderInfo.builder()
                .foodName("참치초밥")
                .price(4000)
                .quantity(3)
                .build()));

        orderService.order("test3",List.of(OrderInfo.builder()
                .foodName("대게초밥")
                .price(3000)
                .quantity(3)
                .build()));

    }
}
