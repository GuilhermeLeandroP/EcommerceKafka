package guilherme.leandro.inventory.event;

import lombok.Data;

@Data
public class StockReservedEvent {
    private String orderId;
    private String productId;
    private int quantity;

    public StockReservedEvent() {}

    public StockReservedEvent(String orderId, String productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
