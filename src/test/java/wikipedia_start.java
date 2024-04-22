import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class wikipedia_start {
    protected static WebDriver driver;
    protected static Actions actions;
    protected static WebDriverWait wait;
    protected static String current;
    @BeforeSuite
    public static void start() {
        driver = new ChromeDriver();
        //setups
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        current= driver.getWindowHandle();
    }
    @AfterSuite
    public static void quit() {
        if (driver != null) {
            driver.quit();
        }
    }
}
