package CON.CON.controller;

import CON.CON.dto.CustomerInfo;
import CON.CON.dto.FoodInfo;
import CON.CON.dto.LoginInfo;
import CON.CON.dto.OrderInfo;
import CON.CON.dto.OrderRequest;
import CON.CON.dto.OrderSearch;
import CON.CON.dto.SearchInfo;
import CON.CON.service.CartService;
import CON.CON.service.CustomerService;
import CON.CON.service.FoodService;
import CON.CON.service.OrderService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final FoodService foodService;
    private final CartService cartService;
    private final OrderService orderService;
    private static String customerId = "";

    public CustomerController(CustomerService customerService, FoodService foodService, CartService cartService,
                              OrderService orderService) {
        this.customerService = customerService;
        this.foodService = foodService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signup";
    } // 회원가입 화면 보이기

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute CustomerInfo info) {
        log.info(info.cno());
        String state = customerService.signUp(info.cno(), info.name(), info.password(), info.phoneNumber());
        log.info(state);
        return "redirect:/";
    } // 회원가입 요청 보내면 회원가입 후 로그인 화면으로 이동

    @PostMapping("/login")
    public String login(@ModelAttribute LoginInfo info) {
        if(customerService.login(info.cno(), info.password())) {
            customerId = info.cno();
            return "redirect:/main";
        }
        else return "redirect:/";
    } // 로그인 요청 회원번호와 비밀번호가 같다면 메뉴를 볼 수 있는 main 화면으로 이동, 아니면 로그인 화면으로 이동

    @GetMapping("/main")
    public String getMainPage(Model model) {
        List<FoodInfo> sushi = foodService.findByCategory("초밥");
        model.addAttribute("sushi", sushi);
        return "sushi";
    } // 초밥 카테고리를 메인으로 보여준다. model로 데이터를 전달해서 프런트 단에서 thymeleaf로 보여준다.

    @GetMapping("/noodle")
    public String getNoodlePage(Model model) {
        List<FoodInfo> noodle = foodService.findByCategory("면류");
        model.addAttribute("noodle", noodle);
        return "noodle";
    }

    @GetMapping("/fried")
    public String getFriedPage(Model model) {
        List<FoodInfo> fried = foodService.findByCategory("튀김류");
        model.addAttribute("fried", fried);
        return "fried";
    }

    @GetMapping("/drink")
    public String getDrinkPage(Model model) {
        List<FoodInfo> drink = foodService.findByCategory("음료");
        model.addAttribute("drink", drink);
        return "drink";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model) {
        List<FoodInfo> cartItems = cartService.getCartItems();
        model.addAttribute("item", cartItems);
        model.addAttribute("customerId", customerId);
        return "cart";
    }

    @GetMapping("/order")
    public String getOrderPage(Model model) {
        List<OrderRequest> requests = orderService.findByCno(customerId);
        for (OrderRequest request : requests) {
            int totalPrice = 0;
            for (OrderInfo order : request.getOrderInfos()) {
                totalPrice += order.price();
            }
            request.setTotalPrice(totalPrice);
        }
        model.addAttribute("orderList", requests);

        return "order";
    }

    @PostMapping("/cart/add")
    public ResponseEntity<Void> addToCart(@RequestBody FoodInfo foodInfo) {
        cartService.addToCart(foodInfo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/order")
    public ResponseEntity<String> addOrder(@RequestBody OrderRequest orderRequest) {
        String customerId = orderRequest.getCustomerId();
        List<OrderInfo> orderInfos = orderRequest.getOrderInfos();
        log.info("customer = {}",customerId);
        orderService.order(customerId, orderInfos);
        return ResponseEntity.ok().body("주문 완료");
    }

    @GetMapping("/search")
    public String getSearchPage() {
        return "search";
    }

    @PostMapping("/search")
    public String getSearchPage(Model model, @ModelAttribute SearchInfo searchInfo) {
        List<FoodInfo> foods = foodService.findByPriceAndWord(searchInfo.minPrice(), searchInfo.maxPrice(),
                searchInfo.keyWord());
        model.addAttribute("foods", foods);
        return "search";
    }

    @GetMapping("/order/search")
    public String getSearchPageDuring(Model model, @ModelAttribute OrderSearch orderSearch) {
        log.info("customerId search : {}", customerId);
        List<OrderRequest> requests = orderService.findByDuring(customerId, orderSearch.start(), orderSearch.end());
        for (OrderRequest request : requests) {
            int totalPrice = 0;
            for (OrderInfo order : request.getOrderInfos()) {
                totalPrice += order.price();
            }
            request.setTotalPrice(totalPrice);
        }
        log.info("customerId : {}",requests.get(0).getCustomerId());
        model.addAttribute("orderList", requests);
        return "order";
    }

    @ResponseBody
    @GetMapping("/revenue")
    public List<String> getRevenueEachCustomer(@ModelAttribute LoginInfo loginInfo) {
        if(loginInfo.cno().equals("c0") && loginInfo.password().equals("admin")) {
            return orderService.printCustomerOrderTotalForCurrentMonth();
        }
        return null;
    }

    @ResponseBody
    @GetMapping("/vip")
    public List<Object[]> getVip(@ModelAttribute LoginInfo loginInfo) {
        if(loginInfo.cno().equals("c0") && loginInfo.password().equals("admin")) {
            return orderService.getSalesRanking();
        }
        return null;
    }
}
