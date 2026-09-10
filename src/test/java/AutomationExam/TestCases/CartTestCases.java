package AutomationExam.TestCases;

import AutomationExam.BaseClass.BaseClass;
import AutomationExam.Pages.CartPage;
import AutomationExam.Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTestCases extends BaseClass {

    @Test(priority = 1)
    public void cartItemIncreaseTest() {
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(loginPage.getProductsText(), "Products");

        cartPage.addToCart();
        Assert.assertEquals(cartPage.checkFullCart(), "Remove");
    }
    @Test(priority = 2)
    public void cartItemDecreaseTest(){
        LoginPage loginPage = new LoginPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(loginPage.getProductsText(), "Products");

        cartPage.addToCart();
        cartPage.removeFromCart();
        Assert.assertEquals(cartPage.checkEmptyCart(),"Add to cart");
    }
}


