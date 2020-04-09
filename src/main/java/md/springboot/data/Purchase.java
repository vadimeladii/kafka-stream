package md.springboot.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Purchase {

    private String firstName;
    private String lastName;
    private String creditCardNumber;
    private String itemPurchased;
    private Double quantity;
    private String purchaseDate;
    private String zipCode;
}
