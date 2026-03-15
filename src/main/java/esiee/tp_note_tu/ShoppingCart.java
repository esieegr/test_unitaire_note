/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esiee.tp_note_tu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gianni
 */
public class ShoppingCart {
 private List<CartItem> items = new ArrayList<>();
 private String promoCode = null;
 public void addItem(String productId, int quantity, double unitPrice) {
 if (productId == null || productId.isBlank()) {
 throw new IllegalArgumentException("Product ID invalide");
 }
 if (quantity <= 0) {
 throw new IllegalArgumentException("Quantité invalide");
 }
 if (unitPrice < 0) {
 throw new IllegalArgumentException("Prix invalide");
 }
 items.add(new CartItem(productId, quantity, unitPrice));
 }
 public void applyPromoCode(String code) {
 if (code == null || code.isBlank()) {
 throw new IllegalArgumentException("Code promo invalide");
 }
 this.promoCode = code;
 }
 public double getTotal() {
 double subtotal = items.stream()
 .mapToDouble(i -> i.quantity() * i.unitPrice())
 .sum();
 if ("PROMO10".equals(promoCode)) return subtotal * 0.90;
 if ("PROMO20".equals(promoCode)) return subtotal * 0.80;
 return subtotal;
 }
 public int getItemCount() { return items.size(); }
 public boolean isEmpty() { return items.isEmpty(); }
{}
 
 public List<CartItem> getItems() {
    return items;
}
}