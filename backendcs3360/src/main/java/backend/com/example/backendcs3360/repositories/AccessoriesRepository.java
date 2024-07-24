package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.dto.AccessoriesDTO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessoriesRepository extends JpaRepository<AccessoriesDTO, Integer> {

}