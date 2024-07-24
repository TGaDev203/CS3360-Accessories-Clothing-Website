package backend.com.example.backendcs3360.controllers;

import backend.com.example.backendcs3360.dto.OrderItemDTO;
import backend.com.example.backendcs3360.dto.OrderItemReqDTO;
import backend.com.example.backendcs3360.models.Cart;
import backend.com.example.backendcs3360.models.ResponseObject;
import backend.com.example.backendcs3360.services.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {
    private OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @CrossOrigin
    // Add an item to the cart
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addItemToCart(@RequestBody OrderItemReqDTO orderItemDTO) {
        OrderItemDTO orderItem = orderItemService.addItemToCart(orderItemDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Add Item to Cart successfully", orderItem));
    }
    @CrossOrigin
    // Update the quantity of an item in the cart
    @PutMapping("change-quantity/{customerId}/{itemId}")
    public ResponseEntity<ResponseObject> updateItemQuantity(@RequestBody OrderItemDTO newOrderItem,
            @PathVariable int customerId, @PathVariable int itemId) {
        OrderItemDTO updatedItem = orderItemService.updateItemQuantity(newOrderItem, customerId, itemId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Update Item to Cart successfully", updatedItem));
    }
    @CrossOrigin
    // Checkout the cart before purchase
    @PostMapping("/checkout/{customerId}")
    public ResponseEntity<ResponseObject> checkout(@PathVariable int customerId) {
        orderItemService.checkout(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Purchase successfully", null));
    }
    @CrossOrigin
    // Remove an item from the cart
    @DeleteMapping("delete/{customerId}/{itemId}")
    public ResponseEntity<ResponseObject> removeItemFromCart(@PathVariable int customerId,
            @PathVariable int itemId) {
        orderItemService.removeItemFromCart(customerId, itemId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Remove Item from Cart successfully", null));
    }
    @CrossOrigin
    // Get purchase history in descending order
    @GetMapping("/historyDesc/{customerId}")
    public ResponseEntity<ResponseObject> getPurchaseHistoryDesc(@PathVariable int customerId) {
        Cart cart = orderItemService.getPurchaseHistoryDesc(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "See History successfully", cart));
    }
    @CrossOrigin
    // Get purchase history in ascending order
    @GetMapping("/historyAsc/{customerId}")
    public ResponseEntity<ResponseObject> getPurchaseHistoryAsc(@PathVariable int customerId) {
        Cart cart = orderItemService.getPurchaseHistoryAsc(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "See History successfully", cart));
    }
    @CrossOrigin
    // Get all items in the cart
    @GetMapping("/get-all/{customerId}")
    public ResponseEntity<ResponseObject> getCartItems(@PathVariable int customerId) {
        Cart cart = orderItemService.getCartItems(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("success", "Get all Cart successfully", cart));
    }
}