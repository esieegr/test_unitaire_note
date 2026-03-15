/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esiee.tp_note_tu;
import java.time.LocalDateTime;

/**
 *
 * @author Gianni
 */
import java.time.LocalDateTime;

public class OrderService {
    private final InventoryRepository inventory;

    public OrderService(InventoryRepository inventory) {
        this.inventory = inventory;
    }

    public Order placeOrder(ShoppingCart cart, String customerId) throws OutOfStockException {
        if (cart.isEmpty()) throw new IllegalStateException("Le panier est vide");
        if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("Customer ID invalide");
        
        for (CartItem item : cart.getItems()) {
            int stock = inventory.getStock(item.productId());
            if (stock < item.quantity()) {
                throw new OutOfStockException("Stock insuffisant pour " + item.productId());
            }
        }
        return new Order(customerId, cart.getTotal(), LocalDateTime.now());
    }
}