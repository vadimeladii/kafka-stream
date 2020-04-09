package md.springboot.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Purchase {

    private String firstName;
    private String lastName;
    private String creditCardNumber;
    private String itemPurchased;
    private Double quantity;
    private String purchaseTime;
}
