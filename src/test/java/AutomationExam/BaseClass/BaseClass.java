package AutomationExam.BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseClass {
    public static WebDriver driver;

    @BeforeMethod
    @SuppressWarnings("null")
    public void setupBrowser(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");           // modern headless mode
        options.addArguments("--no-sandbox");             // required when running as root/jenkins
        options.addArguments("--disable-dev-shm-usage");  // avoids /dev/shm exhaustion in CI
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.get("https://www.saucedemo.com/");    }

    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
