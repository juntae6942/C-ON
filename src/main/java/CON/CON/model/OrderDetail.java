package CON.CON.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OrderId.class)
public class OrderDetail {
    @Id
    private Long itemNo;
    @Id
    @ManyToOne
    private Cart id;
    private Integer quantity;
    private Integer totalPrice;
    @ManyToOne
    private Food foodName;
}
