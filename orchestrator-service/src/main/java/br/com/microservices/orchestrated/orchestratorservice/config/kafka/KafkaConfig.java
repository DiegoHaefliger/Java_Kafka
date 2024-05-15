package br.com.microservices.orchestrated.orchestratorservice.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.BASE_ORCHESTRATOR;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.FINISH_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.FINISH_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.INVENTORY_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.INVENTORY_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.NOTIFTY_ENDING;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PAYMENT_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PAYMENT_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PRODUCT_VALIDATION_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PRODUCT_VALIDATION_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.START_SAGA;

/**
 * Created by
 *
 * @project: Java_Kafka
 * @author: diegohaefliger
 * @Date: 15/05/2024
 */

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private static final Integer PARTITION_COUNT = 1;
    private static final Integer REPLICA_COUNT = 1;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>((consumerConfigs()));
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public NewTopic startSagaTopic() {
        return buildTopic(START_SAGA.getTopic());
    }

    @Bean
    public NewTopic baseOrchestrator() {
        return buildTopic(BASE_ORCHESTRATOR.getTopic());
    }

    @Bean
    public NewTopic finishSuccess() {
        return buildTopic(FINISH_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic finishFail() {
        return buildTopic(FINISH_FAIL.getTopic());
    }

    @Bean
    public NewTopic productValidationSuccess() {
        return buildTopic(PRODUCT_VALIDATION_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic productValidationFail() {
        return buildTopic(PRODUCT_VALIDATION_FAIL.getTopic());
    }

    @Bean
    public NewTopic paymentSuccess() {
        return buildTopic(PAYMENT_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic paymentFail() {
        return buildTopic(PAYMENT_FAIL.getTopic());
    }

    @Bean
    public NewTopic inventorySuccess() {
        return buildTopic(INVENTORY_SUCCESS.getTopic());
    }

    @Bean
    public NewTopic inventoryFail() {
        return buildTopic(INVENTORY_FAIL.getTopic());
    }

    @Bean
    public NewTopic notifyEnding() {
        return buildTopic(NOTIFTY_ENDING.getTopic());
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    private NewTopic buildTopic(String name) {
        return TopicBuilder
                .name(name)
                .replicas(REPLICA_COUNT)
                .partitions(PARTITION_COUNT)
                .build();
    }


}
