package md.springboot.consumer;

import md.springboot.data.PurchaseAccumulator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaPurchaseAccumulatorConsumer {

    @KafkaListener(
            topics = "${kafka.topic.reward}",
            containerFactory = "purchaseAccumulatorListenerContainerFactory")
    public void consume(PurchaseAccumulator purchaseAccumulator) {
        System.out.println("Consumed :: " + purchaseAccumulator);
    }
}
