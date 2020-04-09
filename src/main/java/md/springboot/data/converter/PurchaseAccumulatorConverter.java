package md.springboot.data.converter;

import md.springboot.data.Purchase;
import md.springboot.data.PurchaseAccumulator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PurchaseAccumulatorConverter implements Converter<Purchase, PurchaseAccumulator> {

    @Override
    public PurchaseAccumulator convert(Purchase data) {
        PurchaseAccumulator purchaseAccumulator = new PurchaseAccumulator();
        purchaseAccumulator.setCustomerName(data.getFirstName() + "," + data.getLastName());
        purchaseAccumulator.setPurchaseTotal(data.getQuantity() * data.getPrice());

        return purchaseAccumulator;
    }
}
