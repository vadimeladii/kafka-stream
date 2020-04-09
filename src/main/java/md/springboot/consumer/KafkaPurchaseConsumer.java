package md.springboot.consumer;

import md.springboot.data.Purchase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaPurchaseConsumer {

    @KafkaListener(
            topics = "${kafka.topic.even-output}",
            containerFactory = "purchaseListenerContainerFactory")
    public void consume(Purchase purchase) {
        System.out.println("Consumed :: " + purchase);
    }
}
