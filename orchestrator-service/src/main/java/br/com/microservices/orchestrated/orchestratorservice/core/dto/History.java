package br.com.microservices.orchestrated.orchestratorservice.core.dto;

import br.com.microservices.orchestrated.orchestratorservice.core.enums.EventSourceEnum;
import br.com.microservices.orchestrated.orchestratorservice.core.enums.SagaStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by
 *
 * @project: Java_Kafka
 * @author: diegohaefliger
 * @Date: 15/05/2024
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class History {

    private EventSourceEnum source;
    private SagaStatusEnum status;
    private String message;
    private LocalDateTime createdAt;

}
