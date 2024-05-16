package br.com.microservices.orchestrated.productvalidationservice.core.consumer;

import br.com.microservices.orchestrated.productvalidationservice.core.utils.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by
 *
 * @project: Java_Kafka
 * @author: diegohaefliger
 * @Date: 16/05/2024
 */

@Slf4j
@Component
@AllArgsConstructor
public class ProductValidationConsumer {

    private final JsonUtil jsonUtil;

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-success}")

    public void consumeSuccessEvent(String payload) {
        log.info("Receiving sucess event {} from product-validation-success topic", payload);
        var event = jsonUtil.toEvent(payload);
        log.info("Event: {}", event.toString());
    }

    @KafkaListener(
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.topic.product-validation-fail}")

    public void consumeFailEvent(String payload) {
        log.info("Receiving fail event {} from product-validation-fail topic", payload);
        var event = jsonUtil.toEvent(payload);
        log.info("Event: {}", event.toString());
    }
}
