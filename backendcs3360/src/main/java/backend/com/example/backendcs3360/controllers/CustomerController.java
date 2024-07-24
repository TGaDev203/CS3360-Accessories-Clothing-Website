package backend.com.example.backendcs3360.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import backend.com.example.backendcs3360.dto.CustomerDTO;
import backend.com.example.backendcs3360.models.Customer;
import backend.com.example.backendcs3360.models.ResponseObject;
import backend.com.example.backendcs3360.services.CustomerService;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @CrossOrigin
    @GetMapping("/get-all")
    public ResponseEntity<ResponseObject> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        if (!customers.isEmpty())
        {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "Query all customers successfully", customers));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "No customers found", customers));
        }
    }
    @CrossOrigin
    @GetMapping("/get-by-phone/{phoneNumber}")
    public ResponseEntity<ResponseObject> getCustomerByPhoneNumber(@PathVariable String phoneNumber) {
        Optional<CustomerDTO> customer = customerService.findByPhoneNumber(phoneNumber);
        if(!customer.isEmpty()){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "Customer found by phone number: " + phoneNumber, customer.orElse(null)));
        }
        else{
            return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("error", "No customer found by phone number: " + phoneNumber, customer.orElse(null)));
        }
    }
    @CrossOrigin
    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertCustomer(@RequestBody CustomerDTO newCustomerDTO) {
        Customer customer = customerService.insertCustomer(newCustomerDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "Customer added successfully", customer));
    }
    @CrossOrigin
    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateOrInsertCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.updateCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "Customer updated successfully", customer));
    }
    @CrossOrigin
    @DeleteMapping("/delete/{phoneNumber}")
    public ResponseEntity<ResponseObject> deleteCustomer(@PathVariable String phoneNumber) {
        customerService.deleteCustomerByPhoneNumber(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject("success", "Customer deleted successfully", null));
    }
}