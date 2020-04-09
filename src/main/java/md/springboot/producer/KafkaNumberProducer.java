package md.springboot.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaNumberProducer {

    private long counter = 0;
    private final KafkaTemplate<String, Long> kafkaTemplate;

    @Scheduled(fixedRate = 10000)
    public void produce() {
        System.out.println("Produced :: " + counter);
        this.kafkaTemplate.sendDefault(counter++);
    }
}
