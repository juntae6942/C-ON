package CON.CON.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class OrderId implements Serializable {
    private Long itemNo;
    private Long id;
}
