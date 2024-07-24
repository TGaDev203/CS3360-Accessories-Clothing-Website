package backend.com.example.backendcs3360.models;

import backend.com.example.backendcs3360.dto.CustomerDTO;

//@Entity
//// To assign name of database table corresponds to class Customer
//@Table(name = "customers")
public class Customer 
{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
//    @Column(name = "customerId") // To asign name of colume in database table corresponds to customerId field
    private int customerId;
//    @Column(name = "CustomerName")
    private String customerName;
//    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
//    @Column(name = "address")
    private String address;
    
    // Default constructor
    public Customer(){
    }
    
    // Constructor with parameters
    public Customer(int customerId, String customerName, String phoneNumber, String address)
    {
        this.customerId = customerId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getter and setter
    public int getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(int customerId)
    {
        this.customerId = customerId;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
    
    @Override
    public String toString()
    {
        return "Customer{" + 
        "customerId=" + customerId + 
        ", customerName='" + customerName + '\'' + 
        ", phoneNumber='" + phoneNumber + '\'' + 
        ", address='" + address + '\'' +
        "}";
    }
    
    public CustomerDTO convertToDTO(){
        CustomerDTO newDTO = new CustomerDTO();
        newDTO.setCustomerId(this.customerId);
        newDTO.setCustomerName(this.getCustomerName());
        newDTO.setAddress(this.getAddress());
        newDTO.setPhoneNumber(this.getPhoneNumber());
        return newDTO;
    }
}
