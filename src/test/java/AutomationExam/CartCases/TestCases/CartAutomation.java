package AutomationExam.CartCases.TestCases;

import AutomationExam.CartCases.Base.BaseClass;
import AutomationExam.CartCases.Pages.CartPage;
import AutomationExam.CartCases.Pages.LoginPage;
import AutomationExam.CartCases.Pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static AutomationExam.CartCases.Base.BaseClass.driver;

public class CartAutomation extends BaseClass {
    String username = "visual_user";
    String password = "secret_sauce";
    WebDriverWait wait;

    @BeforeMethod
    public void setupWait(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }


    public void navigateToCartAndValidate(){
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login(username, password);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));

        ProductPage productPage = new ProductPage(driver);
        Assert.assertEquals(productPage.getProductHeroText(), "Products");

        productPage.navigateToCart();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartHeroText(), "Your Cart");
    }


    @Test(groups = {"regression"})
    public void validateLoginAndCartHeroText(){
        navigateToCartAndValidate();
    }

    @Test(groups = {"smoke","regression"})
    public void testCheckoutFeature(){
        navigateToCartAndValidate();

        CartPage cartPage = new CartPage(driver);
        cartPage.navigateToProductPage();
        cartPage.checkoutTest();
    }

    @Test(groups={"regression"})
    public void verifyCorrectProductsAppearInCart(){
        navigateToCartAndValidate();

        CartPage cartPage = new CartPage(driver);
        if (cartPage.isCartFilled()){
            cartPage.removeCartItems();
        }

        cartPage.navigateToProductPage();

        ProductPage productPage = new ProductPage(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));

        Assert.assertEquals(productPage.getProductHeroText(), "Products");

        List<String> expectedItems = productPage.addItemstoCart();

        productPage.navigateToCart();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));

        Assert.assertEquals(cartPage.getCartHeroText(), "Your Cart");

        List<String> actualCartItems = cartPage.getCartItemNames();

        for (String itemName : expectedItems) {
            Assert.assertTrue(actualCartItems.contains(itemName), "Expected item not found in cart: "+itemName);
        }
    }

    @Test(groups = {"regression"})
    public void removeButtonCheck(){
        navigateToCartAndValidate();
        CartPage cartPage = new CartPage(driver);
        cartPage.navigateToProductPage();

        ProductPage productPage = new ProductPage(driver);
        productPage.addItemstoCart();

        productPage.navigateToCart();

        cartPage.removeCartItems();
        Assert.assertTrue(!cartPage.isCartFilled());

    }

    @Test(groups = {"regression","smoke"})
    public void testContinueShoppingBtn(){
        navigateToCartAndValidate();
        CartPage cartPage = new CartPage(driver);

        cartPage.navigateToProductPage();
        ProductPage productPage = new ProductPage(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));

        Assert.assertEquals(productPage.getProductHeroText(),"Products");
    }

//    @Test(groups = {"regression"})
//    public void testHamburgerMenu(){
//
//    }

    @Test(groups = {"regression"})
    public void emptyCartCheckOut(){
        navigateToCartAndValidate();
        CartPage cartPage = new CartPage(driver);

        cartPage.removeCartItems();

        cartPage.clickCheckoutBtn();

        cartPage.validateCheckOutHeroText();


    }
    @Test(groups = {"regression"})
    public void productTitleBtnCheck(){
        navigateToCartAndValidate();
        CartPage cartPage = new CartPage(driver);

        cartPage.validateProductTitleBtnLink();
    }
}
