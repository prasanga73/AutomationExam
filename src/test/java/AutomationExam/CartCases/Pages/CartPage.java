package AutomationExam.CartCases.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    private By cartHeroText = By.cssSelector(".title");
    private By checkoutBtn = By.id("checkout");
    private By checkoutHeroText = By.cssSelector(".title");
    private By continueShoppingBtn = By.id("continue-shopping");
    private By cartItems = By.className("cart_item");
    private By removeBtn = By.cssSelector("button[data-test^='remove']");
    private By productTitleLink = By.cssSelector("[data-test$='title-link']");
    private By cartItemName = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getCartItems(){
        return driver.findElements(cartItems);
    }

    public void removeCartItems(){
        List<WebElement> removeButtons = driver.findElements(removeBtn);
        while (!removeButtons.isEmpty()) {
            removeButtons.get(0).click();
            removeButtons = driver.findElements(removeBtn); //fetching the button list after every click to avoid flaky test failure
        }
    }

    public boolean isCartFilled(){
        return !getCartItems().isEmpty();
    }

    public String getCartHeroText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement heroText = wait.until(ExpectedConditions.visibilityOfElementLocated(cartHeroText));
        return heroText.getText();
    }

    public void clickCheckoutBtn(){
        driver.findElement(checkoutBtn).click();
    }

    public void checkoutTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));

        ProductPage productPage = new ProductPage(driver);

        productPage.addItemstoCart();

        productPage.navigateToCart();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItems));

        if (isCartFilled()) {
            driver.findElement(checkoutBtn).click();
            Assert.assertEquals(driver.findElement(checkoutHeroText).getText(), "Checkout: Your Information");
        }
        else {
            throw new NoSuchElementException("There is no items in the cart");
        }
    }

    public List<String> getCartItemNames(){
        List<WebElement> items = driver.findElements(cartItemName);
        List<String> itemNames = new ArrayList<>();

        for(WebElement item : items){
            itemNames.add(item.getText());
        }

        return itemNames;

    }


    public void navigateToProductPage(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingBtn));

        driver.findElement(continueShoppingBtn).click();
    }

    public void validateCheckOutHeroText(){
        Assert.assertNotEquals(driver.findElement(checkoutHeroText).getText(), "Checkout: Your Information");
    }

    public void validateProductTitleBtnLink(){
        List<WebElement> productTitleLinks = driver.findElements(productTitleLink);
        ProductPage productPage = new ProductPage(driver);

        for (WebElement item : productTitleLinks) {
            item.click();
            Assert.assertEquals(productPage.getBacktoProductText(), "Back to products");
            productPage.navigateToCart();
        }
    }

//    public List<WebElement> getProductsList(){
//        return driver.findElements(cartItems);
//    }











}
