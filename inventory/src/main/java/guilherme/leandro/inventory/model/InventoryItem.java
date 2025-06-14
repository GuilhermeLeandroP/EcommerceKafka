package guilherme.leandro.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class InventoryItem {
    @Id
    private String productId;
    private int quantity;

    public InventoryItem() {}

    public InventoryItem(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
