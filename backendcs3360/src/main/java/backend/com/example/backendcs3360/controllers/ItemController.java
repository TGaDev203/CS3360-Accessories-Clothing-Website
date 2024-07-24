package backend.com.example.backendcs3360.controllers;

import backend.com.example.backendcs3360.dto.ItemDTO;
import backend.com.example.backendcs3360.models.Accessories;
import backend.com.example.backendcs3360.models.Clothes;
import backend.com.example.backendcs3360.models.ResponseObject;
import backend.com.example.backendcs3360.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @CrossOrigin
    @GetMapping("/get-all")
    public ResponseEntity<ResponseObject> getAllProducts(){
        List<ItemDTO> productList = itemService.getAllItems();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Query product successfully", productList)
        );
    }

    @CrossOrigin
    @GetMapping("/get-by-name/{productName}")
    public ResponseEntity<ResponseObject> getProductByName(@PathVariable String productName){
        List<ItemDTO> productList = itemService.getByName(productName);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Query product successfully", productList)
        );
    }
    @CrossOrigin
    @GetMapping("/get-all-asc")
    public ResponseEntity<ResponseObject> getAllProductsByPriceAsc() {
        List<ItemDTO> productList =  itemService.getAllItemsByPriceAsc();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Query product successfully", productList)
        );
    }
    @CrossOrigin
    @GetMapping("/get-all-desc")
    public ResponseEntity<ResponseObject> getAllProductsByPriceDesc() {
        List<ItemDTO> productList =  itemService.getAllItemsByPriceDesc();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Query product successfully", productList)
        );
    }
    @CrossOrigin
    @PostMapping("/post-accessories")
    public ResponseEntity<ResponseObject> postNewAccessories(@RequestBody Accessories item){
        itemService.insertNewAccessories(item);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Insert new product successfully", null)
        );
    }
    @CrossOrigin
    @PostMapping("/post-clothes")
    public ResponseEntity<ResponseObject> postNewClothes(@RequestBody Clothes item){
        itemService.insertNewClothes(item);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Insert new product successfully", null)
        );
    }
    @CrossOrigin
    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<ResponseObject> deleteItem(@PathVariable int itemId){
        itemService.deleteItemById(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Insert new product successfully", null)
        );
    }

}
