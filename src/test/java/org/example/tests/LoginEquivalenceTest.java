package org.example.tests;

import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginEquivalenceTest extends BaseTest {
    @DataProvider(name = "loginTestData")
    public Object[][] loginTestData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "https://www.saucedemo.com/inventory.html", true}, // CP1 - Válido
                {"usuario_malo", "secret_sauce", "Epic sadface: Username and password do not match any user in this service", false}, // CP2 - Usuario incorrecto
                {"", "secret_sauce", "Epic sadface: Username is required", false}, // CP3 - Usuario vacío
                {"standard_user", "contraseña_mala", "Epic sadface: Username and password do not match any user in this service", false}, // CP6 - Contraseña incorrecta
                {"standard_user", "", "Epic sadface: Password is required", false} // CP7 - Contraseña vacía
        };
    }

    @Test(dataProvider = "loginTestData")
    public void testLoginEquivalencePartitioning(String username, String password, String expectedMessage, boolean shouldLogout) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (expectedMessage.startsWith("https")) {
            Assert.assertEquals(driver.getCurrentUrl(), expectedMessage, "User should be redirected to inventory page.");

            if (shouldLogout) {
                WebDriverWait wait = new WebDriverWait(driver, 10);

                WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
                menuButton.click();
                WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
                logoutButton.click();

                wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
            }
            } else {
                String errorText = driver.findElement(By.cssSelector("[data-test='error']")).getText();
                Assert.assertEquals(errorText, expectedMessage, "Error message should match expected.");
            }
    }
}

