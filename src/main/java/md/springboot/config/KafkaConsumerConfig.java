package md.springboot.config;

import md.springboot.data.Purchase;
import md.springboot.data.PurchaseAccumulator;
import md.springboot.data.PurchasePattern;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, Purchase> consumerPurchaseStoreFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(Purchase.class));
    }

    @Bean
    public ConsumerFactory<String, PurchasePattern> consumerPurchasePatternFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(PurchasePattern.class));
    }

    @Bean
    public ConsumerFactory<String, PurchaseAccumulator> consumerAccumulatorPatternFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(PurchaseAccumulator.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Purchase> purchaseStoreListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Purchase> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerPurchaseStoreFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PurchasePattern> purchasePatternListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PurchasePattern> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerPurchasePatternFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PurchaseAccumulator> purchaseAccumulatorListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PurchaseAccumulator> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerAccumulatorPatternFactory());
        return factory;
    }
}
