/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esiee.tp_note_tu;

/**
 *
 * @author Gianni
 */
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    ShoppingCartTest.class,
    ShoppingCartPromoTest.class,
    OrderServiceTest.class
})
class EcommerceTestSuite {
    // Cette classe ne contient aucune méthode de test.
    // Elle sert uniquement à regrouper et lancer les tests déclarés.
}