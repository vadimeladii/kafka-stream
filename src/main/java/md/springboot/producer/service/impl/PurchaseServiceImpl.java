package md.springboot.producer.service.impl;

import lombok.RequiredArgsConstructor;
import md.springboot.data.Purchase;
import md.springboot.producer.service.PurchaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    @Value("${kafka.topic.input}")
    private String inputTopic;

    private final KafkaTemplate<String, Purchase> kafkaTemplate;

    @Override
    public void create(Purchase purchase) {
        System.out.println("Produced :: " + purchase);
        this.kafkaTemplate.send(inputTopic, UUID.randomUUID().toString(), purchase);
    }
}
