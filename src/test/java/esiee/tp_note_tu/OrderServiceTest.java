/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esiee.tp_note_tu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Gianni
 */
class OrderServiceTest {
    // On simule un stock toujours disponible (100 unités)
    private InventoryRepository stock = productId -> 100;
    private OrderService service;
    private ShoppingCart cart;

    @BeforeEach
    void setUp() {
        service = new OrderService(stock);
        cart = new ShoppingCart();
    }

    @Test
    void commandeValideDoitRetournerOrder() throws OutOfStockException {
        cart.addItem("PROD-001", 2, 15.0);
        Order order = service.placeOrder(cart, "CLIENT-42");
        assertNotNull(order);
        assertEquals(30.0, order.total());
    }

    @Test
    void panierVideDoitLeverIllegalStateException() {
        assertThrows(IllegalStateException.class, 
            () -> service.placeOrder(cart, "C1"));
    }

    @Test
    void customerIdNulDoitLeverException() {
        cart.addItem("PROD-001", 1, 10.0);
        assertThrows(IllegalArgumentException.class, 
            () -> service.placeOrder(cart, null));
    }

    @Test
    void stockInsuffisantDoitLeverOutOfStockException() {
        // On crée un stock spécifique pour ce test qui simule une rupture
        InventoryRepository stockFaible = productId -> 0;
        OrderService serviceStockFaible = new OrderService(stockFaible);
        
        cart.addItem("PROD-001", 5, 10.0);
        assertThrows(OutOfStockException.class, 
            () -> serviceStockFaible.placeOrder(cart, "CLIENT-42"));
    }
}