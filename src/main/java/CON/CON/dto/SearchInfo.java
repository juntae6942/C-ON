package CON.CON.dto;

import java.time.LocalDateTime;

public record SearchInfo(
        Integer minPrice,
        Integer maxPrice,
        String keyWord
) {
    public SearchInfo(Integer minPrice, Integer maxPrice, String keyWord) {
        this.minPrice = (minPrice != null) ? minPrice : 0;
        this.maxPrice = (maxPrice != null) ? maxPrice : Integer.MAX_VALUE;
        this.keyWord = (keyWord != null) ? keyWord : "";
    }
}
