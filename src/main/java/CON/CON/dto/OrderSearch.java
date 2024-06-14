package CON.CON.dto;

import java.time.LocalDate;

public record OrderSearch(
        LocalDate start,
        LocalDate end
) {
}
