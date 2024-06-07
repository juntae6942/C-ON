package CON.CON.dto;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record OrderInfo(
        String foodName,
        Integer price,
        Integer quantity
) {
}
