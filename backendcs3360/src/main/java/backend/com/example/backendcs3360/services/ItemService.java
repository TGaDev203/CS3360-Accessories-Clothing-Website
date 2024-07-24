package backend.com.example.backendcs3360.services;

import backend.com.example.backendcs3360.dto.AccessoriesDTO;
import backend.com.example.backendcs3360.dto.ClothesDTO;
import backend.com.example.backendcs3360.dto.ItemDTO;
import backend.com.example.backendcs3360.models.Accessories;
import backend.com.example.backendcs3360.models.Clothes;
import backend.com.example.backendcs3360.repositories.AccessoriesRepository;
import backend.com.example.backendcs3360.repositories.ClothesRepository;
import backend.com.example.backendcs3360.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ClothesRepository clothesRepository;
    private final AccessoriesRepository accessoriesRepository;

    public ItemService(ItemRepository itemRepository, ClothesRepository clothesRepository,
        AccessoriesRepository accessoriesRepository) {
        this.itemRepository = itemRepository;
        this.clothesRepository = clothesRepository;
        this.accessoriesRepository = accessoriesRepository;
    }

    public List<ItemDTO> getAllItems() {
        List<ItemDTO> itemFromDB = itemRepository.findAll();
        // List<Item> itemToSend = itemFromDB.stream().map(Item::)
        // List<Item> itemFromDB = accessoriesRepository.findAll();

        return itemFromDB;
    }

    public ClothesDTO insertNewClothes(Clothes newClothes) {
        ClothesDTO newDTO = newClothes.convertToDTO();
        return clothesRepository.save(newDTO);
    }

    public AccessoriesDTO insertNewAccessories(Accessories newAccessories) {
        AccessoriesDTO newDTO = newAccessories.convertToDTO();
        newDTO.setItemId(1);
        return accessoriesRepository.save(newDTO);
    }

    public List<ItemDTO> getByName(String productName) {
        return itemRepository.findByProductNameContainingIgnoreCase(productName);
    }

    public List<ItemDTO> getAllItemsByPriceAsc() {
        return itemRepository.findByOrderByPriceAsc();
    }

    public List<ItemDTO> getAllItemsByPriceDesc() {
        return itemRepository.findByOrderByPriceDesc();
    }
    public void deleteItemById(int id){
        itemRepository.deleteById(id);
    }
}
