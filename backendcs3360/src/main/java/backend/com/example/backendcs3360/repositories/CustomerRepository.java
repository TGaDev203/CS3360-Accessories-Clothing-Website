package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.dto.CustomerDTO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDTO, Integer> {
    Optional<CustomerDTO> findByPhoneNumber(String phoneNumber);
    Optional<CustomerDTO> findById (int customerId);
}