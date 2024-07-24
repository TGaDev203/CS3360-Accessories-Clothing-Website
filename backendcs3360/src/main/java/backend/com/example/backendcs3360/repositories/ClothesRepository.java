package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.dto.ClothesDTO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<ClothesDTO, Integer> {

}