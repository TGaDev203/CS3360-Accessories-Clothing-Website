package backend.com.example.backendcs3360.models;

import backend.com.example.backendcs3360.dto.AccessoriesDTO;
//@Entity
//@Table(name = "accessories")

public class Accessories extends Item {
//    @Column(name = "type")
    private String type;
    
//    @Column(name = "material")
    private String material;
    
//    @Column(name = "weight")
    private double weight;

    public Accessories() {
        super();
    }

    public Accessories(int itemId, String productName, double price, String description, String type, String material, double weight, String imagePath) {
        super(itemId, productName, price, description, imagePath);
        this.type = type;
        this.material = material;
        this.weight = weight;
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

    @Override
    public String toString() {
        return super.toString() + " Accessories {" +
                "type='" + type + '\'' +
                ", material='" + material + '\'' +
                ", weight=" + weight +
                '}';
    }

    public AccessoriesDTO convertToDTO() {
        AccessoriesDTO newDTO = new AccessoriesDTO();
        newDTO.setMaterial(this.getMaterial());
        newDTO.setType(this.getType());
        newDTO.setWeight(this.getWeight());
        newDTO.setDescription(this.getDescription());
        newDTO.setPrice(this.getPrice());
        newDTO.setProductName(this.getProductName());
        newDTO.setItemId(this.getItemId());
        newDTO.setImagePath(this.getImagePath());
        return newDTO;
    }
}

