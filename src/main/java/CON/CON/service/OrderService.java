package CON.CON.service;

import CON.CON.dto.CustomerSalesRank;
import CON.CON.dto.OrderInfo;
import CON.CON.dto.OrderRequest;
import CON.CON.model.Cart;
import CON.CON.model.Customer;
import CON.CON.model.Food;
import CON.CON.model.OrderDetail;
import CON.CON.repository.OrderDetailRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {
    private final OrderDetailRepository orderDetailRepository;
    private final CartService cartService;
    private Long sequence = 0L;

    private final DateTimeFormatter formatter = new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toFormatter();
    public OrderService(OrderDetailRepository orderDetailRepository, CartService cartService) {
        this.orderDetailRepository = orderDetailRepository;
        this.cartService = cartService;
    }

    public void order(String customerId, List<OrderInfo> orderInfos) {
        Cart cart = cartService.saveCart(customerId);
        for (OrderInfo orderInfo : orderInfos) {

            if (orderInfo.price() == null || orderInfo.quantity() == null) {
                throw new IllegalArgumentException("Price or quantity cannot be null");
            }
            orderDetailRepository.save(OrderDetail.builder()
                    .itemNo(sequence++)
                    .id(cart)
                    .foodName(Food.builder()
                            .foodName(orderInfo.foodName())
                            .price(orderInfo.price())
                            .build())
                            .quantity(orderInfo.quantity())
                            .totalPrice(orderInfo.price() * orderInfo.quantity())
                    .build());
        }
        cartService.clearCart();
    }

    public List<OrderRequest> findByCno(String cno) {
        List<Cart> carts = cartService.findAllByCno(cno);
        List<OrderRequest> requests = new ArrayList<>();
        for (Cart cart : carts) {
            List<OrderInfo> list = orderDetailRepository.findAllById(cart).stream()
                    .map(orderDetail -> OrderInfo.builder()
                            .foodName(orderDetail.getFoodName().getFoodName())
                            .price(orderDetail.getTotalPrice())
                            .quantity(orderDetail.getQuantity()).build()).toList();
            OrderRequest request = OrderRequest.builder()
                    .localDateTime(LocalDateTime.parse(cart.getDateTime().toString()).format(formatter))
                    .customerId(cart.getCno().getCno())
                    .orderInfos(list)
                    .build();
            requests.add(request);
        }

        return requests;
    }

    public List<String> printCustomerOrderTotalForCurrentMonth() {
        List<Object[]> results = orderDetailRepository.findCustomerOrderTotalForCurrentMonth();
        List<String> response = new ArrayList<>();
        for (Object[] result : results) {
            Customer customer = (Customer) result[0];
            int totalPrice = ((Number)result[1]).intValue();
            response.add("Customer: " + customer.getCno() + ", Total Price: " + totalPrice);
        }
        return response;
    }

    public List<Object[]> getSalesRanking() {
        return orderDetailRepository.getSalesRanking();
    }
}
