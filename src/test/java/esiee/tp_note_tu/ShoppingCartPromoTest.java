/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esiee.tp_note_tu;

/**
 *
 * @author Gianni
 */
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartPromoTest {

    @ParameterizedTest(name = "Code {0} doit donner un total de {1}")
    @CsvSource({
        ", 100.0",            // Pas de promo
        "PROMO10, 90.0",      // -10% sur 100.0
        "PROMO20, 80.0",      // -20% sur 100.0
        ", 200.0",            // Pas de promo, qty > 1 (20 articles à 10.0 = 200.0)
        "PROMO10, 180.0",     // -10% sur 200.0
        "PROMO20, 160.0"      // -20% sur 200.0
    })
    void getTotalDoitAppliquerLaBonneReduction(String code, double expectedTotal) {
        ShoppingCart cart = new ShoppingCart();
        // Déterminer la quantité en fonction du total attendu pour varier les scénarios
        int qty = (expectedTotal >= 160.0) ? 20 : 10;
        cart.addItem("PROD-001", qty, 10.0);
        
        if (code != null && !code.isBlank()) {
            cart.applyPromoCode(code.trim());
        }
        assertEquals(expectedTotal, cart.getTotal(), 0.001);
    }
    
    @ParameterizedTest
    @CsvSource({
        ", 1, 10.0",       // productId null
        "'', 1, 10.0",     // productId vide
        "PROD-001, 0, 10.0",// quantité nulle
        "PROD-001, -5, 10.0",// quantité négative
        "PROD-001, 1, -1.0"  // prix négatif
    })
            
    void addItemDoitLeverExceptionPourDonneesInvalides(String id, int qty, double price) {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> cart.addItem(id, qty, price));
    }
}