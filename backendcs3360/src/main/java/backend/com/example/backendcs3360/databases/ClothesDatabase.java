//package backend.com.example.backendcs3360.databases;
//
//import backend.com.example.backendcs3360.models.Clothes;
//import backend.com.example.backendcs3360.repositories.ClothesRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ClothesDatabase {
//
//    private final ClothesRepository clothesRepository;
//
//    @Autowired
//    public ClothesDatabase(ClothesRepository clothesRepository) {
//        this.clothesRepository = clothesRepository;
//    }
//
//    public List<Clothes> getAllClothes() {
//        return clothesRepository.findAll();
//    }
//
//    public Clothes getClothesById(int id) {
//        return clothesRepository.findById(id).orElse(null);
//    }
//
//    public Clothes saveClothes(Clothes clothes) {
//        return clothesRepository.save(clothes);
//    }
//
//    public void deleteClothes(int id) {
//        clothesRepository.deleteById(id);
//    }
//}