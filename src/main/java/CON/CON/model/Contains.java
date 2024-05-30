package CON.CON.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contains {
    @Id
    @ManyToOne
    private Food foodName;
    @Id
    @ManyToOne
    private Category categoryName;
}
