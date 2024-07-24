//package backend.com.example.backendcs3360.databases;
//
//import backend.com.example.backendcs3360.models.Accessories;
//import backend.com.example.backendcs3360.repositories.AccessoriesRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AccessoriesDatabase {
//
//    private final AccessoriesRepository accessoriesRepository;
//
//    @Autowired
//    public AccessoriesDatabase(AccessoriesRepository accessoriesRepository) {
//        this.accessoriesRepository = accessoriesRepository;
//    }
//
//    public List<Accessories> getAllAccessories() {
//        return accessoriesRepository.findAll();
//    }
//
//    public Accessories getAccessoriesById(int id) {
//        return accessoriesRepository.findById(id).orElse(null);
//    }
//
//    public Accessories saveAccessories(Accessories accessories) {
//        return accessoriesRepository.save(accessories);
//    }
//
//    public void deleteAccessories(int id) {
//        accessoriesRepository.deleteById(id);
//    }
//}