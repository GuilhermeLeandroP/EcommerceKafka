package guilherme.leandro.inventory.event;

import lombok.Data;

@Data
public class StockInsufficientEvent {
    private String orderId;
    private String productId;
    private int requestedQuantity;
    private int availableQuantity;

    public StockInsufficientEvent() {}

    public StockInsufficientEvent(String orderId, String productId, int requestedQuantity, int availableQuantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }
}
