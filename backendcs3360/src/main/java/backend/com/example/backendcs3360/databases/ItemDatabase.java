//package backend.com.example.backendcs3360.databases;
//
// import backend.com.example.backendcs3360.models.Accessories;
// import backend.com.example.backendcs3360.models.Clothes;
//
// import backend.com.example.backendcs3360.repositories.ItemRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Bean;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Service;
//
// Configuration class: manage beans
// @Configuration
// public class ItemDatabase {
//     // Logger: print log
//     private final Logger logger = LoggerFactory.getLogger(ItemDatabase.class);
//
//     // Bean: manage objects
//     @Bean
//     CommandLineRunner initDatabase(ItemRepository itemRepository) {
//         return new CommandLineRunner() {
//             @Override
//             public void run(String... args) throws Exception {
//                 Accessories accessories1 = new Accessories(1, "Necklace", 50.0, "Beautiful necklace", "Vong tay", "Diamond", 0.2);
//                 Accessories accessories2 = new Accessories(2, "Earring", 20.0, "Beautiful earring", "Khuyen tai", "Diamond", 0.1);
//                 Clothes clothes1 = new Clothes(2, "Shirt", 30.0, "Comfortable shirt", "Adidas", "M");
//                 logger.info("insert data: " + itemRepository.save(accessories1));
//                 logger.info("insert data: " + itemRepository.save(accessories2));
//                 logger.info("insert data: " + itemRepository.save(clothes1));
//             }
//         };
//     }
//
// }
//
//import backend.com.example.backendcs3360.models.Item;
//import backend.com.example.backendcs3360.repositories.ItemRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ItemDatabase {
//
//    private final ItemRepository itemRepository;
//
//    @Autowired
//    public ItemDatabase(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }
//
//    public List<Item> getAllItems() {
//        return itemRepository.findAll();
//    }
//
//    public Item getItemById(int id) {
//        return itemRepository.findById(id).orElse(null);
//    }
//
//    public Item saveItem(Item item) {
//        return itemRepository.save(item);
//    }
//
//    public void deleteItem(int id) {
//        itemRepository.deleteById(id);
//    }
//}
