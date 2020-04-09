package md.springboot.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Purchase {

    private String firstName;
    private String lastName;
    private String creditCardNumber;
    private String itemPurchased;
    private Long quantity;
    private Double price;
    private String purchaseDate;
    private String zipCode;
}
