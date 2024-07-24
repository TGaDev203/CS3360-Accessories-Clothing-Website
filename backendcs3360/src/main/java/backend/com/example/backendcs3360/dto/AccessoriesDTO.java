package backend.com.example.backendcs3360.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import backend.com.example.backendcs3360.models.Accessories;

@Entity
@Table(name = "accessories")
public class AccessoriesDTO extends ItemDTO{
    public AccessoriesDTO(int itemId, String productName, double price, String description, String imagePath, String type, String material, double weight) {
        super(itemId, productName, price, description, imagePath);
    }
    @Column(name = "type")
    private String type;

    @Column(name = "material")
    private String material;

    @Column(name = "weight")
    private double weight;

    public AccessoriesDTO() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Accessories convertToAccessories() {
        Accessories newItem = new Accessories();
        newItem.setItemId(this.getItemId());
        newItem.setDescription(this.getDescription());
        newItem.setPrice(this.getPrice());
        newItem.setProductName(this.getProductName());
        newItem.setImagePath(this.getImagePath());
        newItem.setType(this.getType());
        newItem.setMaterial(this.getMaterial());
        newItem.setWeight(this.getWeight());
        return newItem;
    }
}
