package backend.com.example.backendcs3360.services;

import backend.com.example.backendcs3360.dto.OrderItemDTO;
import backend.com.example.backendcs3360.dto.ItemDTO;
import backend.com.example.backendcs3360.dto.CustomerDTO;

import backend.com.example.backendcs3360.dto.OrderItemReqDTO;
import backend.com.example.backendcs3360.models.Cart;
import backend.com.example.backendcs3360.models.OrderItem;
import backend.com.example.backendcs3360.repositories.CustomerRepository;
import backend.com.example.backendcs3360.repositories.ItemRepository;
import backend.com.example.backendcs3360.repositories.OrderItemRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, CustomerRepository customerRepository,
            ItemRepository itemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public OrderItemDTO addItemToCart(OrderItemReqDTO orderItemDTO) {
        int customerId = orderItemDTO.getCustomerId();
        int itemId = orderItemDTO.getItemId();
        List<OrderItemDTO> cartItems = orderItemRepository.findByCustomer_CustomerIdAndDateOfPurchaseIsNull(customerId);
        OrderItemDTO orderItem = null;

        // Check if the item already exists in the cart
        for (OrderItemDTO cartItem : cartItems) {
            if (cartItem.getItem().getItemId() == itemId) {
                // If the item exists, update the quantity and save
                cartItem.setQuantity(cartItem.getQuantity() + orderItemDTO.getQuantity());
                return orderItemRepository.save(cartItem);
            }
        }

        // If the item does not exist in the cart, create a new order item
        orderItem = new OrderItemDTO();
        if (cartItems.isEmpty()) {
            orderItem.setOrderCode(generateUniqueOrderCode());
        } else {
            orderItem.setOrderCode(cartItems.get(0).getOrderCode());
        }

        CustomerDTO customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        ItemDTO item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        orderItem.setCustomer(customer);
        orderItem.setItem(item);
        orderItem.setQuantity(orderItemDTO.getQuantity());

        return orderItemRepository.save(orderItem);
    }
    @Transactional
    public OrderItemDTO updateItemQuantity(OrderItemDTO newOrderItem, int customerId, int itemId) {
        OrderItemDTO orderItem = orderItemRepository
                .findByCustomer_CustomerIdAndItem_ItemIdAndDateOfPurchaseIsNull(customerId, itemId);
        if (orderItem != null) {
            // Update the quantity of the item

            orderItem.setQuantity(newOrderItem.getQuantity());
            return orderItemRepository.save(orderItem);
        } else {
            throw new RuntimeException("Item not found in cart");
        }
    }

    @Transactional
    // Checkout the cart to purchase the items
    public void checkout(int customerId) {
        List<OrderItemDTO> cartItems = orderItemRepository.findByCustomer_CustomerIdAndDateOfPurchaseIsNull(customerId);
        // If the cart is not empty, set the date of purchase to the current date and
        // save the order items.
        if (!cartItems.isEmpty()) {
            for (OrderItemDTO orderItem : cartItems) {
                orderItem.setDateOfPurchase(new Date());
                orderItemRepository.save(orderItem);
            }
        }
    }

    // Generate a unique order code
    private String generateUniqueOrderCode() {
        return UUID.randomUUID().toString();
    }

    // Remove an item from the cart
    @Transactional
    public void removeItemFromCart(int customerId, int itemId) {
        OrderItemDTO orderItem = orderItemRepository
                .findByCustomer_CustomerIdAndItem_ItemIdAndDateOfPurchaseIsNull(customerId, itemId);
        if (orderItem != null) {
            orderItemRepository.delete(orderItem);
        } else {
            throw new RuntimeException("Item not found in cart");
        }
    }

    @Transactional
    public Cart getPurchaseHistoryDesc(int customerId) {
        List<OrderItemDTO> itemsDTO = orderItemRepository
                .findByCustomer_CustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseDesc(customerId);
        List<OrderItem> items = itemsDTO.stream()
                .map(OrderItemDTO::convertToOrderItems)
                .collect(Collectors.toList());
        double total = items.stream()
                .mapToDouble(orderItem -> orderItem.getQuantity() * orderItem.getItem().getPrice())
                .sum();
        String phoneNumber = "";
        if (!items.isEmpty()) {
            phoneNumber = items.get(0).getCustomer().getPhoneNumber();
        }
        Cart cart = new Cart();
        cart.setOrderItems(items);
        cart.setPhoneNumber(phoneNumber);
        cart.setTotal(total);

        return cart;
    }

    @Transactional
    public Cart getPurchaseHistoryAsc(int customerId) {
        List<OrderItemDTO> itemsDTO = orderItemRepository
                .findByCustomer_CustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseAsc(customerId);
        List<OrderItem> items = itemsDTO.stream()
                .map(OrderItemDTO::convertToOrderItems)
                .collect(Collectors.toList());
        double total = items.stream()
                .mapToDouble(orderItem -> orderItem.getQuantity() * orderItem.getItem().getPrice())
                .sum();
        String phoneNumber = "";
        if (!items.isEmpty()) {
            phoneNumber = items.get(0).getCustomer().getPhoneNumber();
        }
        Cart cart = new Cart();
        cart.setOrderItems(items);
        cart.setPhoneNumber(phoneNumber);
        cart.setTotal(total);

        return cart;
    }

    @Transactional
    public Cart getCartItems(int customerId) {
        List<OrderItemDTO> itemsDTO = orderItemRepository
                .findByCustomer_CustomerIdAndDateOfPurchaseIsNull(customerId);

        // If itemsDTO is empty, return an empty Cart
        if (itemsDTO.isEmpty()) {
            return new Cart();
        }

        List<OrderItem> items = itemsDTO.stream()
                .map(OrderItemDTO::convertToOrderItems)
                .collect(Collectors.toList());
        double total = items.stream()
                .mapToDouble(orderItem -> orderItem.getQuantity() * orderItem.getItem().getPrice())
                .sum();
        String phoneNumber = "";
        if (!items.isEmpty()) {
            phoneNumber = items.get(0).getCustomer().getPhoneNumber();
        }
        Cart cart = new Cart();
        cart.setOrderItems(items);
        cart.setPhoneNumber(phoneNumber);
        cart.setTotal(total);

        return cart;
    }
}
