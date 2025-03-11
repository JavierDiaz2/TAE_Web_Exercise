package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Random;

public class ProductsPage extends BasePage {

    @FindBy(className = "inventory_item")
    private List<WebElement> products;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addRandomProduct() {

        Random random = new Random();
        int randomIndex = random.nextInt(products.size());
        WebElement product = products.get(randomIndex);
        product.findElement(org.openqa.selenium.By.tagName("button")).click();
    }

    public void goToCart() {
        cartButton.click();
    }
}
