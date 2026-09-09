package AutomationExam.LoginTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By userName = By.cssSelector("#user-name");
    private By password = By.cssSelector("#password");
    private By loginBtn = By.cssSelector("#login-button");
    private By productsText = By.cssSelector(".title");
    private By loginErrorText = By.cssSelector("h3[data-test='error']");

    public void enterUsername(String user) {
        driver.findElement(userName).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }

    public String getProductsText() {
        return driver.findElement(productsText).getText();
    }

    public String getLoginErrorText() {
        return driver.findElement(loginErrorText).getText();
    }

    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLoginBtn();
    }
}
