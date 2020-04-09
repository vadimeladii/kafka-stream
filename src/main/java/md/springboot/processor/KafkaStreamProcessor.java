package md.springboot.processor;

import md.springboot.data.Purchase;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

@Component
public class KafkaStreamProcessor {

    @Value("${kafka.topic.input}")
    private String inputTopic;

    @Value("${kafka.topic.even-output}")
    private String outputTopic;

    @Bean
    public KStream<String, Purchase> kStream(StreamsBuilder kStreamBuilder) {
        KStream<String, Purchase> stream = kStreamBuilder.stream(inputTopic, Consumed.with(Serdes.String(), Serdes.serdeFrom(
                new JsonSerializer<>(), new JsonDeserializer<>(Purchase.class))));

        stream
                .peek((s, purchase) -> System.out.println(purchase))
                .to(outputTopic,
                        Produced.with(Serdes.String(), Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(Purchase.class))));

        return stream;
    }
}
