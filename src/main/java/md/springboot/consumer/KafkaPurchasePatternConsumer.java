package md.springboot.consumer;

import md.springboot.data.PurchasePattern;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaPurchasePatternConsumer {

    @KafkaListener(
            topics = "${kafka.topic.pattern}",
            containerFactory = "purchasePatternListenerContainerFactory")
    public void consume(PurchasePattern purchasePattern) {
        System.out.println("Consumed :: " + purchasePattern);
    }
}
