package backend.com.example.backendcs3360.dto;

public class OrderItemReqDTO {
    private int customerId;
    private int itemId;
    private int quantity;

    public OrderItemReqDTO(int customerId, int itemId, int quantity) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
