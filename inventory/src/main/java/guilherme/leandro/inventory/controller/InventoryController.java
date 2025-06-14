package guilherme.leandro.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import guilherme.leandro.inventory.model.InventoryItem;
import guilherme.leandro.inventory.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("/{productId}")
    public InventoryItem getInventory(@PathVariable String productId) {
        return service.getInventory(productId);
    }

    @GetMapping
    public List<InventoryItem> getAllInventory() {
        return service.getAllInventory();
    }

    @PutMapping("/{productId}")
    public InventoryItem updateInventory(@PathVariable String productId, @RequestBody InventoryItem item) {
        return service.updateInventory(productId, item.getQuantity());
    }

    @PostMapping("/reserve")
    public void reserveStock(@RequestParam String orderId, @RequestParam String productId, @RequestParam int quantity) {
        service.reserveStock(orderId, productId, quantity);
    }

    @PostMapping("/release")
    public void releaseStock(@RequestParam String productId, @RequestParam int quantity) {
        service.releaseStock(productId, quantity);
    }
}