package CON.CON.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Food {
    @Id
    private String foodName;
    private Integer price;
}
