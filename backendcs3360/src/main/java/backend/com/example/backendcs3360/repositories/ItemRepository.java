package backend.com.example.backendcs3360.repositories;

import backend.com.example.backendcs3360.dto.ItemDTO;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemDTO, Integer> {
        // List<ItemDTO> findByProductNameContaining (String productName);
        List<ItemDTO> findByProductNameContainingIgnoreCase(String productName);

        List<ItemDTO> findByOrderByPriceAsc();

        List<ItemDTO> findByOrderByPriceDesc();
}