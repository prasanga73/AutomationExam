package AutomationExam.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    public WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addToCartButtons = By.cssSelector("#add-to-cart-sauce-labs-backpack");
    private By removeButtons = By.cssSelector("#remove-sauce-labs-backpack");

    public void addToCart() {
        driver.findElement(addToCartButtons).click();
    }

    public String checkFullCart() {
        return driver.findElement(removeButtons).getText();
    }

    public void removeFromCart() {
        driver.findElement(removeButtons).click();
    }

    public String checkEmptyCart(){
        return driver.findElement(addToCartButtons).getText();
    }
}
