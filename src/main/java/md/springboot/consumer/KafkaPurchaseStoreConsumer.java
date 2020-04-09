package md.springboot.consumer;

import md.springboot.data.Purchase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaPurchaseStoreConsumer {

    @KafkaListener(
            topics = "${kafka.topic.store}",
            containerFactory = "purchaseStoreListenerContainerFactory")
    public void consume(Purchase purchase) {
        System.out.println("Consumed :: " + purchase);
    }
}
