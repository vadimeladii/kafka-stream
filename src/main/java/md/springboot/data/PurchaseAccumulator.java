package md.springboot.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PurchaseAccumulator {

    private String customerName;
    private Double purchaseTotal;
}
