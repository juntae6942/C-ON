package CON.CON.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class ContainId implements Serializable {
    private String foodName;
    private String categoryName;
}
