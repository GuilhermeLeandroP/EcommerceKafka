package guilherme.leandro.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import guilherme.leandro.inventory.event.StockInsufficientEvent;
import guilherme.leandro.inventory.event.StockReservedEvent;
import guilherme.leandro.inventory.kafka.StockEventProducer;
import guilherme.leandro.inventory.model.InventoryItem;
import guilherme.leandro.inventory.repository.InventoryRepository;

public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    @Autowired
    private StockEventProducer producer;

    public InventoryItem getInventory(String productId) {
        return repository.findById(productId).orElse(null);
    }

    public List<InventoryItem> getAllInventory() {
        return repository.findAll();
    }

    public InventoryItem updateInventory(String productId, int quantity) {
        InventoryItem item = new InventoryItem(productId, quantity);
        return repository.save(item);
    }

    public void reserveStock(String orderId, String productId, int quantity) {
        Optional<InventoryItem> optional = repository.findById(productId);
        if (optional.isPresent()) {
            InventoryItem item = optional.get();
            if (item.getQuantity() >= quantity) {
                item.setQuantity(item.getQuantity() - quantity);
                repository.save(item);
                producer.sendStockReservedEvent(new StockReservedEvent(orderId, productId, quantity));
            } else {
                producer.sendStockInsufficientEvent(new StockInsufficientEvent(orderId, productId, quantity, item.getQuantity()));
            }
        } else {
            producer.sendStockInsufficientEvent(new StockInsufficientEvent(orderId, productId, quantity, 0));
        }
    }

    public void releaseStock(String productId, int quantity) {
        InventoryItem item = repository.findById(productId).orElse(null);
        if (item != null) {
            item.setQuantity(item.getQuantity() + quantity);
            repository.save(item);
        }
    }
}
