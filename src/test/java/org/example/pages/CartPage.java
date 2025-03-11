package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(id = "checkout")
    private WebElement checkoutButton;


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCartEmpty() {
        return driver.findElements(By.className("cart_item")).isEmpty();
    }

    public void removeFirstItem() {
        List<WebElement> items = driver.findElements(By.className("cart_item"));
        if (!items.isEmpty()) {
            WebElement firstItem = items.get(0);
            WebElement removeButton = firstItem.findElement(By.xpath(".//button[contains(text(), 'Remove')]"));
            System.out.println("Found remove button: " + removeButton.getText());
            removeButton.click();
            System.out.println("Removed item from cart");
        } else {
            System.out.println("No items found in cart.");
        }
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }
}

