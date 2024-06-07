package CON.CON.service;

import CON.CON.exception.DuplicatedError;
import CON.CON.model.Customer;
import CON.CON.repository.CustomerRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String signUp(String cno, String name, String password, String phoneNumber) {
        if(customerRepository.findById(cno).isPresent()) {
            throw new DuplicatedError("중복된 아이디입니다.");
        }
        customerRepository.save(Customer.builder()
                .cno(cno)
                .name(name)
                .password(password)
                .phoneNumber(phoneNumber)
                .build());
        return "회원 가입이 완료되었습니다.";
    }

    public boolean login(String cno, String password) {
        Optional<Customer> customer = customerRepository.findById(cno);
        return customer.map(value -> value.getPassword().equals(password)).orElse(false);
    }
}
