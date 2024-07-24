package backend.com.example.backendcs3360.dto;

import backend.com.example.backendcs3360.models.Clothes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clothes")

public class ClothesDTO extends ItemDTO{
    @Column(name = "brand")
    private String brand;
    @Column(name = "size")
    private String size;

    public ClothesDTO(int item_id, String productName, double price, String description, String imagePath) {
        super(item_id, productName, price, description, imagePath);
    }

    public ClothesDTO() {
        super();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    public Clothes convertToClothes  (){
        Clothes newItem = new Clothes();
        newItem.setItemId(this.getItemId());
        newItem.setBrand(this.getBrand());
        newItem.setDescription(this.getDescription());
        newItem.setPrice(this.getPrice());
        newItem.setSize(this.getSize());
        newItem.setProductName(this.getProductName());
        newItem.setImagePath(this.getImagePath());
        return newItem;
    }
}
