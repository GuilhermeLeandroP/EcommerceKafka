package guilherme.leandro.inventory.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import guilherme.leandro.inventory.event.OrderCreatedEvent;
import guilherme.leandro.inventory.service.InventoryService;

@Service
public class OrderCreatedConsumer {

    @Autowired
    private InventoryService inventoryService;

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void consumeOrderCreated(OrderCreatedEvent event) {
        inventoryService.reserveStock(event.getOrderId(), event.getProductId(), event.getQuantity());
    }
}