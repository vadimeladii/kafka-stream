package md.springboot.data;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchasePattern {

    private String zipCode;
    private String item;
    private String date;
}
