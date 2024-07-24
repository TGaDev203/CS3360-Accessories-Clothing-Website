//package backend.com.example.backendcs3360.databases;
//
//import backend.com.example.backendcs3360.models.OrderItem;
//import backend.com.example.backendcs3360.repositories.OrderItemRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class OrderItemDatabase {
//
//    private final OrderItemRepository orderItemRepository;
//
//    @Autowired
//    public OrderItemDatabase(OrderItemRepository orderItemRepository) {
//        this.orderItemRepository = orderItemRepository;
//    }
//
//    public List<OrderItem> getAllOrderItems() {
//        return orderItemRepository.findAll();
//    }
//
//    public OrderItem getOrderItemById(int id) {
//        return orderItemRepository.findById(id).orElse(null);
//    }
//
//    public OrderItem saveOrderItem(OrderItem orderItem) {
//        return orderItemRepository.save(orderItem);
//    }
//
//    public void deleteOrderItem(int id) {
//        orderItemRepository.deleteById(id);
//    }
//
//    public OrderItem updateOrderItemQuantity(int id, int newQuantity) {
//        OrderItem orderItem = orderItemRepository.findById(id).orElse(null);
//        if (orderItem != null) {
//            orderItem.setQuantity(newQuantity);
//            orderItemRepository.save(orderItem);
//        }
//        return orderItem;
//    }
//}