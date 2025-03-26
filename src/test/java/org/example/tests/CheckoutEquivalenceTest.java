package org.example.tests;
import org.example.pages.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutEquivalenceTest extends BaseTest {
    @BeforeMethod
    public void preconditions() {
        loginPrecondition();
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addRandomProduct();
        productsPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();
    }
    @DataProvider(name = "checkoutTestData")
    public Object[][] checkoutTestData() {
        return new Object[][] {
                {"Javier", "Diaz", "57", true}, // CP9, CP13, CP17 - Válido
                {"", "Diaz", "57", "Error: First Name is required"}, // CP10 - Nombre vacío
                {"Javier", "", "57", "Error: Last Name is required"}, // CP14 - Apellido vacío
                {"Javier", "Diaz", "", "Error: Postal Code is required"}, // CP18 - Código Postal vacío
                {"Javier", "Diaz", "-57", "Error: Postal Code is invalid"}, // CP19 - Código Postal negativo
                {"Javier", "Diaz", "0", "Error: Postal Code is invalid"}, // CP20 - Código Postal en cero
                {"Javier", "Diaz", "Letr@s", "Error: Postal Code is invalid"} // CP21 - Código Postal con caracteres especiales
        };
    }
    @Test(dataProvider = "checkoutTestData")
    public void testCheckoutEquivalencePartitioning(String firstName, String lastName, String postalCode, Object expectedResult) {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutInformation(firstName, lastName, postalCode);
        if (expectedResult.equals(true)) {
            checkoutPage.completePurchase();
            Assert.assertTrue(checkoutPage.isPurchaseSuccessful(), "The purchase should be successful.");
        } else {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            String errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))).getText();

            Assert.assertEquals(errorText, expectedResult, "Error message should match expected.");
        }
    }
}
