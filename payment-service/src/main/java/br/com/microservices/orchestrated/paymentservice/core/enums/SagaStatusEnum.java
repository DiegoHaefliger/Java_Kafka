package br.com.microservices.orchestrated.paymentservice.core.enums;

/**
 * Created by
 *
 * @project: Java_Kafka
 * @author: diegohaefliger
 * @Date: 15/05/2024
 */

public enum SagaStatusEnum {

    SUCCESS,
    ROLLBACK_PENDING,
    FAIL;
}
