package br.com.microservices.orchestrated.orchestratorservice.core.saga;

import static br.com.microservices.orchestrated.orchestratorservice.core.enums.EventSourceEnum.INVENTORY_SERVICE;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.EventSourceEnum.ORCHESTRATOR;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.EventSourceEnum.PAYMENT_SERVICE;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.EventSourceEnum.PRODUCT_VALIDATION_SERVICE;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.SagaStatusEnum.FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.SagaStatusEnum.ROLLBACK_PENDING;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.SagaStatusEnum.SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.FINISH_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.FINISH_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.INVENTORY_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.INVENTORY_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PAYMENT_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PAYMENT_SUCCESS;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PRODUCT_VALIDATION_FAIL;
import static br.com.microservices.orchestrated.orchestratorservice.core.enums.TopicsEnum.PRODUCT_VALIDATION_SUCCESS;

/**
 * Created by
 *
 * @project: Java_Kafka
 * @author: diegohaefliger
 * @Date: 22/05/2024
 */


public final class SagaHandler {

    private SagaHandler() {

    }

    public static final Object[][] SAGA_HANDLER = {
            {ORCHESTRATOR, SUCCESS, PRODUCT_VALIDATION_SUCCESS},
            {ORCHESTRATOR, FAIL, FINISH_FAIL},

            {PRODUCT_VALIDATION_SERVICE, ROLLBACK_PENDING, PRODUCT_VALIDATION_FAIL},
            {PRODUCT_VALIDATION_SERVICE, FAIL, FINISH_FAIL},
            {PRODUCT_VALIDATION_SERVICE, SUCCESS, PAYMENT_SUCCESS},

            {PAYMENT_SERVICE, ROLLBACK_PENDING, PAYMENT_FAIL},
            {PAYMENT_SERVICE, FAIL, PRODUCT_VALIDATION_FAIL},
            {PAYMENT_SERVICE, SUCCESS, INVENTORY_SUCCESS},

            {INVENTORY_SERVICE, ROLLBACK_PENDING, INVENTORY_FAIL},
            {INVENTORY_SERVICE, FAIL, PAYMENT_FAIL},
            {INVENTORY_SERVICE, SUCCESS, FINISH_SUCCESS}
    };

    public static final int EVENT_SOURCE_INDEX = 0;
    public static final int SAGA_STATUS_INDEX = 1;
    public static final int TOPIC_INDEX = 2;
}

