package br.com.microservices.orchestrated.inventoryservice.core.repository;

import br.com.microservices.orchestrated.inventoryservice.core.model.OrderInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by
 *
 * @project: Java_Kafka
 * @author: diegohaefliger
 * @Date: 18/05/2024
 */

public interface OrderInventoryRepository extends JpaRepository<OrderInventory, Integer> {

    List<OrderInventory> findByOrderIdAndTransactionId(String orderId, String transactionId);

    Boolean existsByOrderIdAndTransactionId(String orderId, String transactionId);
}

