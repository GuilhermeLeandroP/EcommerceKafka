package guilherme.leandro.inventory.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import guilherme.leandro.inventory.event.StockInsufficientEvent;
import guilherme.leandro.inventory.event.StockReservedEvent;

@Service
public class StockEventProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendStockReservedEvent(StockReservedEvent event) {
        kafkaTemplate.send("stock-reserved", event);
    }

    public void sendStockInsufficientEvent(StockInsufficientEvent event) {
        kafkaTemplate.send("stock-insufficient", event);
    }
}
