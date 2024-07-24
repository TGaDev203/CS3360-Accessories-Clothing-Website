package backend.com.example.backendcs3360.models;

import backend.com.example.backendcs3360.dto.ClothesDTO;


//@Entity
//@Table(name = "clothes")

public class Clothes extends Item {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "brand")
    private String brand;
//    @Column(name = "size")
    private String size;

    public Clothes(){
        super();
    }

    public Clothes(int itemId, String productName, double price, String description, String brand, String size, String imagePath) {
        super(itemId, productName, price, description, imagePath);
        this.brand = brand;
        this.size = size;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getSize(){
        return size;
    }

    public void setSize(String size){
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + " Clothes {" +
                "brand='" + brand + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
    public ClothesDTO convertToDTO(){
        ClothesDTO newDTO = new ClothesDTO();
        newDTO.setBrand(this.getBrand());
        newDTO.setSize(this.getSize());
        newDTO.setDescription(this.getDescription());
        newDTO.setPrice(this.getPrice());
        newDTO.setProductName(this.getProductName());
        newDTO.setItemId(this.getItemId());
        newDTO.setImagePath(this.getImagePath());
        return newDTO;
    }
}