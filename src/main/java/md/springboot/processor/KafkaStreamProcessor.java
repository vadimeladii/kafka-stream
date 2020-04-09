package md.springboot.processor;

import lombok.RequiredArgsConstructor;
import md.springboot.data.Purchase;
import md.springboot.data.PurchaseAccumulator;
import md.springboot.data.PurchasePattern;
import md.springboot.data.converter.PurchaseAccumulatorConverter;
import md.springboot.data.converter.PurchasePatternConverter;
import md.springboot.data.converter.PurchaseStoreConverter;
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
@RequiredArgsConstructor
public class KafkaStreamProcessor {

    @Value("${kafka.topic.source}")
    private String sourceTopic;

    @Value("${kafka.topic.reward}")
    private String rewardTopic;

    @Value("${kafka.topic.pattern}")
    private String patternTopic;

    @Value("${kafka.topic.store}")
    private String storeTopic;

    private final PurchaseStoreConverter purchaseStoreConverter;
    private final PurchasePatternConverter purchasePatternConverter;
    private final PurchaseAccumulatorConverter purchaseAccumulatorConverter;

    @Bean
    public KStream<String, Purchase> kStream(StreamsBuilder kStreamBuilder) {
        KStream<String, Purchase> stream = kStreamBuilder.stream(sourceTopic, Consumed.with(Serdes.String(), Serdes.serdeFrom(
                new JsonSerializer<>(), new JsonDeserializer<>(Purchase.class))));

        KStream<String, Purchase> maskingPurchaseKStream = stream.mapValues((s, purchase) -> purchaseStoreConverter.convert(purchase));

        maskingPurchaseKStream
                .mapValues((s, purchase) -> purchaseAccumulatorConverter.convert(purchase))
                .to(rewardTopic,
                        Produced.with(Serdes.String(), Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(PurchaseAccumulator.class))));

        maskingPurchaseKStream
                .mapValues((s, purchase) -> purchasePatternConverter.convert(purchase))
                .to(patternTopic,
                        Produced.with(Serdes.String(), Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(PurchasePattern.class))));

        maskingPurchaseKStream
                .to(storeTopic,
                        Produced.with(Serdes.String(), Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(Purchase.class))));

        return stream;
    }
}
