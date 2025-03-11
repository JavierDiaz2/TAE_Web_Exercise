package org.example.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.example.pages.LoginPage;


public class LoginTest extends BaseTest {

    /*@Test
    public void testLoginPageLoads() {
        boolean isLoginButtonPresent = driver.findElement(By.id("login-button")).isDisplayed();
        Assert.assertTrue(isLoginButtonPresent, "Login button should be displayed.");
    }*/

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "User should be redirected to inventory page.");
    }


}
