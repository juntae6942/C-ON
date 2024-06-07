package CON.CON.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class OrderRequest {
        private String localDateTime;
        private String customerId;
        private List<OrderInfo> orderInfos;
        private Integer totalPrice;
}
