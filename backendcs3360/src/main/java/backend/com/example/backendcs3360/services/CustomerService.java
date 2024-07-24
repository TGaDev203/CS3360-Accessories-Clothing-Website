package backend.com.example.backendcs3360.services;

import backend.com.example.backendcs3360.dto.CustomerDTO;
import backend.com.example.backendcs3360.models.Customer;
import backend.com.example.backendcs3360.repositories.CustomerRepository;
import backend.com.example.backendcs3360.repositories.OrderItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderItemRepository itemRepository;

    public CustomerService(CustomerRepository repository, OrderItemRepository itemRepository) {
        this.customerRepository = repository;
        this.itemRepository = itemRepository;
    }

    // Method getAllCustomers
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customers = customerRepository.findAll();
        // if (customers.isEmpty()) {
        //     throw new RuntimeException("No customers found.");
        // }
        return customers;
    }


    // Method findByPhoneNumber
    public Optional<CustomerDTO> findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

// Method insertCustomer
public Customer insertCustomer(CustomerDTO newCustomerDTO) {
    if (newCustomerDTO.getCustomerName() == null || newCustomerDTO.getCustomerName().isEmpty()) {
        throw new RuntimeException("Name cannot be empty");
    }

    if (newCustomerDTO.getPhoneNumber() == null || newCustomerDTO.getPhoneNumber().isEmpty()) {
        throw new RuntimeException("Phone number cannot be empty");
    }

    if (!newCustomerDTO.getPhoneNumber().matches("\\d{10}")) {
        throw new RuntimeException("Phone number must be a 10-digit number");
    }

    Optional<CustomerDTO> existingCustomerDTO = customerRepository
            .findByPhoneNumber(newCustomerDTO.getPhoneNumber());
    if (existingCustomerDTO.isPresent()) {
        // Return the existing customer if phone number exists
        return existingCustomerDTO.get().convertToCustomerModel();
    } else {
        // Save new customer if phone number doesn't exist
        CustomerDTO savedCustomerDTO = customerRepository.save(newCustomerDTO);
        // Convert the saved CustomerDTO to Customer and return it
        return savedCustomerDTO.convertToCustomerModel();
    }
}

    // Method updateCustomer
    public Customer updateCustomer(CustomerDTO customerDTO) {
        Optional<CustomerDTO> existingCustomerOptional = customerRepository
                .findByPhoneNumber(customerDTO.getPhoneNumber());
        if (existingCustomerOptional.isPresent()) {
            // Update the existing customer
            CustomerDTO existingCustomer = existingCustomerOptional.get();
            existingCustomer.setCustomerName(customerDTO.getCustomerName());
            // existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
            existingCustomer.setAddress(customerDTO.getAddress());
            CustomerDTO updatedCustomerDTO = customerRepository.save(existingCustomer);
            // Convert the updated CustomerDTO to Customer and return it
            return updatedCustomerDTO.convertToCustomerModel();
        } else {
            throw new RuntimeException("Customer not found with phone number: " + customerDTO.getPhoneNumber());
        }
    }

    // Method deleteCustomerByPhoneNumber
    public void deleteCustomerByPhoneNumber(String phoneNumber) {
        Optional<CustomerDTO> existingCustomerOptional = customerRepository.findByPhoneNumber(phoneNumber);
        if (existingCustomerOptional.isPresent()) {
            // Delete the related items
            itemRepository.deleteByCustomerId(existingCustomerOptional.get().getCustomerId());
            // Delete the existing customer
            customerRepository.delete(existingCustomerOptional.get());
        } else {
            throw new RuntimeException("Customer not found with phone number: " + phoneNumber);
        }
    }

}
