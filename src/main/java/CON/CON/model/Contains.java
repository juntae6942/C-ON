package CON.CON.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ContainId.class)
public class Contains {
    @Id
    @ManyToOne
    @JoinColumn(name = "foodName")
    private Food foodName;
    @Id
    @ManyToOne
    @JoinColumn(name = "categoryName")
    private Category categoryName;
}
