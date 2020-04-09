package md.springboot.producer.controller.impl;

import lombok.RequiredArgsConstructor;
import md.springboot.data.Purchase;
import md.springboot.producer.controller.PurchaseController;
import md.springboot.producer.service.PurchaseService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PurchaseControllerImpl implements PurchaseController {

    private final PurchaseService purchaseService;

    @Override
    public void create(Purchase purchase) {
        purchaseService.create(purchase);
    }
}
