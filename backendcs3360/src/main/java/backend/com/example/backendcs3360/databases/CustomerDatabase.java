//package backend.com.example.backendcs3360.databases;
//
//import backend.com.example.backendcs3360.models.Customer;
//import backend.com.example.backendcs3360.repositories.CustomerRepository;
//
//// import org.slf4j.Logger;
//// import org.slf4j.LoggerFactory;
//// import org.springframework.boot.CommandLineRunner;
//// import org.springframework.context.annotation.Bean;
//// import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.util.List;
//
//
//// @Configuration
//// public class CustomerDatabase {
////     private static final Logger logger = LoggerFactory.getLogger(CustomerDatabase.class);
//
////     @Bean
////     CommandLineRunner initDatabase(CustomerRepository customerRepository) {
////         return new CommandLineRunner() {
////             @Override
////             public void run(String... args) throws Exception {
////                 Customer customer1 = new Customer(123, "Tuan Anh", "0123456789", "26 Hang Ma, HN");
////                 logger.info("insert data: " + customerRepository.save(customer1));
////             }
////         };
////     }
//// }
//
//@Service
//public class CustomerDatabase {
//
//    private final CustomerRepository customerRepository;
//
//    @Autowired
//    public CustomerDatabase(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    public List<Customer> getAllCustomers() {
//        return customerRepository.findAll();
//    }
//
//    public Customer getCustomerById(int id) {
//        return customerRepository.findById(id).orElse(null);
//    }
//
//    public Customer saveCustomer(Customer customer) {
//        return customerRepository.save(customer);
//    }
//
//    public void deleteCustomer(int id) {
//        customerRepository.deleteById(id);
//    }
//}