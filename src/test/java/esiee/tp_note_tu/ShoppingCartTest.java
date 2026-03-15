/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esiee.tp_note_tu;

/**
 *
 * @author Gianni
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ShoppingCartTest {

    @Test
    void addItemDoitAugmenterLeNombreArticles() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        // Act
        cart.addItem("PROD-001", 2, 15.0);
        // Assert
        assertEquals(1, cart.getItemCount());
    }

    @Test
    void getTotalDoitRetournerSommePrixUnitairesMultipliesParQuantite() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        // Act
        cart.addItem("PROD-001", 2, 15.0);
        // Assert
        assertEquals(30.0, cart.getTotal());
    }

    @Test
    void cartVideDoitAvoirIsEmptyTrue() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        // Assert
        assertTrue(cart.isEmpty());
    }
    
    @Test
    void productIdNulDoitLeverException() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, 
            () -> cart.addItem(null, 1, 10.0));
    }

    @Test
    void productIdVideDoitLeverException() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, 
            () -> cart.addItem("", 1, 10.0));
    }

    @Test
    void quantiteNulleDoitLeverException() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, 
            () -> cart.addItem("PROD-001", 0, 15.0));
    }

    @Test
    void quantiteNegativeDoitLeverException() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, 
            () -> cart.addItem("PROD-001", -3, 15.0));
    }

    @Test
    void prixNegatifDoitLeverException() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, 
            () -> cart.addItem("PROD-001", 1, -5.0));
    }

    @Test
    void codePromoVideDoitLeverException() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, 
            () -> cart.applyPromoCode(""));
    }
    
    @Test
    void quantiteUneDoitFonctionner() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        // Act
        cart.addItem("PROD-001", 1, 9.99);
        // Assert
        assertEquals(9.99, cart.getTotal());
    }

    @Test
    void articleGratuitDoitEtreAccepte() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        // Act
        cart.addItem("PROMO-FREE", 1, 0.0);
        // Assert
        assertEquals(0.0, cart.getTotal());
    }

    @Test
    void prixElevéDoitFonctionner() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        // Act
        cart.addItem("PROD-LUXE", 1, 999.99);
        // Assert
        assertEquals(999.99, cart.getTotal());
    }

    @Test
    void panierAvecUnSeulArticleDoitFonctionner() {
        // Arrange
        ShoppingCart cart = new ShoppingCart();
        // Act
        cart.addItem("PROD-001", 1, 10.0);
        // Assert
        assertEquals(1, cart.getItemCount());
        assertFalse(cart.isEmpty());
    }
}