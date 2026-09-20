package AutomationExam.CartCases.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By userName = By.id("user-name");
    private By Password = By.id("password");
    private By loginButton = By.id("login-button");



    public void login(String username, String password) {
        driver.findElement(userName).sendKeys(username);
        driver.findElement(Password).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
