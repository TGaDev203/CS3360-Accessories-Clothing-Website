package backend.com.example.backendcs3360.models;

import java.util.Date;

public class OrderItem {
    private int listOfItemsId;
    private Customer customer;
    private Item item;
    private int quantity;
    private String orderCode;
    private Date dateOfPurchase;

    public OrderItem() {
    }

    public OrderItem(int listOfItemsId, Customer customer, Item item, int quantity, String orderCode, Date dateOfPurchase) {
        this.listOfItemsId = listOfItemsId;
        this.customer = customer;
        this.item = item;
        this.quantity = quantity;
        this.orderCode = orderCode;
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getListOfItemsId() {
        return listOfItemsId;
    }

    public void setListOfItemsId(int listOfItemsId) {
        this.listOfItemsId = listOfItemsId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order_id=" + listOfItemsId +
                ", item=" + item +
                ", quantity=" + quantity +
                ", order_code='" + orderCode + '\'' +
                ", order_date='" + dateOfPurchase + '\'' +
                '}';
    }
}