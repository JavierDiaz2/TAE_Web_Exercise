package org.example.tests;

import org.example.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class PurchaseTest extends BaseTest {

    @BeforeMethod
    public void preconditions() {
        loginPrecondition();
    }

    @Test
    public void testPurchaseProduct() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addRandomProduct();
        productsPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutInformation("Javier", "Diaz", "57");
        checkoutPage.completePurchase();

        Assert.assertTrue(checkoutPage.isPurchaseSuccessful(), "The purchase should be successful.");
    }


}
