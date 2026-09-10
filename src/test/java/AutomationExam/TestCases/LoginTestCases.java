package AutomationExam.TestCases;

import AutomationExam.BaseClass.BaseClass;
import AutomationExam.Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestCases extends BaseClass {

    // Main 3 conditions

    @Test(priority = 1)
    public void ValidUsernameValidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        String actualText = loginPage.getProductsText();
        Assert.assertEquals(actualText, "Products");
    }

    @Test(priority = 2)
    public void ValidUsernameInvalidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");
        String errorMsg = loginPage.getLoginErrorText();
        Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 3)
    public void ValidUsernameEmptyPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.clickLoginBtn();
        String errorMsg = loginPage.getLoginErrorText();
        Assert.assertEquals(errorMsg, "Epic sadface: Password is required");
    }

    //Invalid username combinations

    @Test(priority = 4)
    public void InvalidUsernameValidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "secret_sauce");
        String errorMsg = loginPage.getLoginErrorText();
        Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 5)
    public void InvalidUsernameInvalidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "wrong_password");
        String errorMsg = loginPage.getLoginErrorText();
        Assert.assertEquals(errorMsg, "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(priority = 6)
    public void InvalidUsernameEmptyPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("invalid_user");
        loginPage.clickLoginBtn();
        String errorMsg = loginPage.getLoginErrorText();
        Assert.assertEquals(errorMsg, "Epic sadface: Password is required");
    }

    //Empty username combinations

    @Test(priority = 7)
    public void EmptyUsernameValidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginBtn();
        String errorMsg = loginPage.getLoginErrorText();
        Assert.assertEquals(errorMsg, "Epic sadface: Username is required");
    }

    @Test(priority = 8)
    public void EmptyUsernameInvalidPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword("wrong_password");
        loginPage.clickLoginBtn();
        String errorMsg = loginPage.getLoginErrorText();
        Assert.assertEquals(errorMsg, "Epic sadface: Username is required");
    }

    @Test(priority = 9)
    public void EmptyUsernameEmptyPassword(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginBtn();
        String errorMsg = loginPage.getLoginErrorText();
        Assert.assertEquals(errorMsg, "Epic sadface: Username is required");
    }
}
