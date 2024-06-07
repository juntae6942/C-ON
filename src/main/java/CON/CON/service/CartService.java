package CON.CON.service;

import CON.CON.dto.FoodInfo;
import CON.CON.model.Cart;
import CON.CON.model.Customer;
import CON.CON.repository.CartRepository;
import CON.CON.repository.CustomerRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class CartService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private List<FoodInfo> cart = new ArrayList<>();

    public CartService(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    public Cart saveCart(String customerId) {
        return cartRepository.save(Cart.builder()
                .dateTime(LocalDateTime.now())
                .cno(customerRepository.findById(customerId).get())
                .build());
    }
    public void addToCart(FoodInfo foodInfo) {
        if (cart.stream().anyMatch(food -> food.name().equals(foodInfo.name()))) {
           return;
        }
        cart.add(foodInfo);
    }

    public List<FoodInfo> getCartItems() {
        return cart;
    }

    public Cart findById(long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    public List<Cart> findAllByCno(String cno) {
        Optional<Customer> customer = customerRepository.findById(cno);
        return cartRepository.findAllByCno(customer.get());
    }

    public void clearCart() {
        cart.clear();
    }
}
