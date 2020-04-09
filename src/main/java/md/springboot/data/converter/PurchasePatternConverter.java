package md.springboot.data.converter;

import md.springboot.data.Purchase;
import md.springboot.data.PurchasePattern;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PurchasePatternConverter implements Converter<Purchase, PurchasePattern> {

    @Override
    public PurchasePattern convert(Purchase data) {
        PurchasePattern purchasePattern = new PurchasePattern();
        purchasePattern.setZipCode(data.getZipCode());
        purchasePattern.setItem(data.getItemPurchased());
        purchasePattern.setDate(data.getPurchaseDate());

        return purchasePattern;
    }
}
