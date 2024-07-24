package backend.com.example.backendcs3360.dto;

import backend.com.example.backendcs3360.models.Customer;
import jakarta.persistence.*;

@Entity
// To assign name of database table corresponds to class Customer
@Table(name = "customers")
public class CustomerDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    @Column(name = "customer_id") 
    private int customerId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "address")
    private String address;

    // @OneToMany(fetch = FetchType.EAGER)
    // private List<OrderItemDTO> orderItems;

    public CustomerDTO() {
    }

    public CustomerDTO(int customerId, String customerName, String phoneNumber, String address) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Customer convertToCustomerModel(){
        Customer customer = new Customer();
        customer.setCustomerId(this.getCustomerId());
        customer.setCustomerName(this.getCustomerName());
        customer.setAddress(this.getAddress());
        customer.setPhoneNumber(this.getPhoneNumber());
        return customer;
    }
}
