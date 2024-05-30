package CON.CON.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    private Long id;
    private LocalDateTime dateTime;
    @ManyToOne
    private Customer cno;
}
