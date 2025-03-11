package org.example.tests;

import org.example.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void preconditions() {
        loginPrecondition();
    }

    @Test
    public void testRemoveProducts() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addRandomProduct();
        productsPage.addRandomProduct();
        productsPage.addRandomProduct();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeFirstItem();
        cartPage.removeFirstItem();
        cartPage.removeFirstItem();

        Assert.assertTrue(cartPage.isCartEmpty(), "The cart should be empty.");
    }
}
