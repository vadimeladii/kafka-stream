package md.springboot.data.converter;

import md.springboot.data.Purchase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PurchaseStoreConverter implements Converter<Purchase, Purchase> {

    @Override
    public Purchase convert(Purchase data) {
        Purchase purchase = new Purchase();
        purchase.setFirstName(data.getFirstName());
        purchase.setLastName(data.getLastName());
        purchase.setItemPurchased(data.getItemPurchased());
        purchase.setPurchaseTime(data.getPurchaseTime());
        purchase.setQuantity(data.getQuantity());
        purchase.setCreditCardNumber((StringUtils.overlay(
                data.getCreditCardNumber(),
                StringUtils.repeat("X", data.getCreditCardNumber().length() - 4), 0, data.getCreditCardNumber().length() - 4)));

        return purchase;
    }
}
