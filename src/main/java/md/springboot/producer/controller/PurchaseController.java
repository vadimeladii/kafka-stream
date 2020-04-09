package md.springboot.producer.controller;

import md.springboot.data.Purchase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("purchase")
public interface PurchaseController {

    @PostMapping
    void create(@RequestBody Purchase purchase);
}
