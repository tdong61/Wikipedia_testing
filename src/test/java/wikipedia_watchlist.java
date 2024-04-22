import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class wikipedia_watchlist extends wikipedia_start{
    int counter = 0;
    @Test(priority = 0)
    void setup() throws InterruptedException {
        //setup from wikipedia page
        driver.get("https://www.wikipedia.org/");
        driver.manage().window().maximize();
        //go to English
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("link-box"))).click();
        driver.findElement(By.className("mw-logo-tagline")).click();
        Thread.sleep(2000);
        boolean available = driver.findElements(By.id("pt-login-2")).size() > 0;
        if(available) {
            driver.findElement(By.id("pt-login-2")).click();
            // enter username and passowrd
            wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("wpName1")))).sendKeys("Tdtddkk3123s");
            driver.findElement(By.id("wpPassword1")).sendKeys("asdfe2znna");
            // check to keep login
            driver.findElement(By.id("wpRemember")).click();
            // login
            driver.findElement(By.id("wpLoginAttempt")).click();
        }
        Thread.sleep(2000);
        //watchlist
        driver.findElement(By.xpath("//*[@id=\"pt-watchlist-2\"]/a/span[1]")).click();
    }
    @Test(priority = 1)
    void view_relevant_changes() throws InterruptedException {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"mw-content-text\"]/div[2]/div/div/div[2]/div[1]/div/div[2]/div/div[1]/div/div[1]/span[1]/bdi"))));
        element.click();
//        List<WebElement> button = driver.findElements(By.className("oo-ui-labelElement-label"));
//        //navigate and click latest revision
//        for(WebElement element : button) {
//            if(element.getText().equals("Latest revision")) {
//                element.click();
//                break;
//            }
//        }
        //uncheck latest revision
        driver.findElement(By.xpath("//*[@id=\"ooui-883\"]/div/div/div[1]/div/div/span[1]/span")).click();
        Thread.sleep(1000);
        //filter results
        driver.findElement(By.xpath("//*[@id=\"ooui-10\"]")).click();
        Thread.sleep(1000);
        //100days
        driver.findElement(By.xpath("//*[@id=\"ooui-9\"]/div[1]/div/div/div[1]/div[1]/div/div[2]/a")).click();
        Thread.sleep(1000);
        //filter results
        driver.findElement(By.xpath("//*[@id=\"ooui-10\"]/a/span[3]")).click();
        Thread.sleep(1000);
        //checkbox
        driver.findElement(By.id("ooui-7")).click();
        Thread.sleep(1000);
        //7days
        //filter results
        driver.findElement(By.xpath("//*[@id=\"ooui-10\"]/a/span[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"ooui-9\"]/div[1]/div/div/div[2]/div[2]/div/div[3]/a/span[2]")).click();
        Thread.sleep(1000);
        //hide and show
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/div/div/div[2]/div[1]/div/div[1]/div[3]/span/a/span[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/div/div/div[2]/div[1]/div/div[1]/div[3]/span/a/span[2]")).click();
        Thread.sleep(1000);
    }
    @Test(priority = 2)
    void watchlist_filter() throws InterruptedException {
        //save filter
        driver.findElement(By.xpath("//*[@id=\"ooui-3\"]/a/span[1]")).click();
        driver.findElement(By.id("ooui-4")).sendKeys("Test");
        //check default
        driver.findElement(By.id("ooui-5")).click();
        //create filter
        driver.findElement(By.xpath("//*[@id=\"ooui-2\"]/div[1]/div[2]/div/div[3]/span[2]/a")).click();
        //clear filter
        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/div/div/div[2]/div[1]/div/div[2]/div/div[3]/span/a/span[1]")).click();
//        //click rename, delete filter
//        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/div/div/div[1]/div/div[3]/div/div[2]/span/a/span[3]")).click();
//        driver.findElement(By.xpath("//*[@id=\"ooui-889\"]/div/div/span")).click();
//        driver.findElement(By.xpath("//*[@id=\"ooui-895\"]/span[3]")).click();
//        driver.findElement(By.xpath("//*[@id=\"ooui-894\"]/div/div/div[1]/div/input")).sendKeys("123");
//        //delete filter
//        driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/div/div/div[1]/div/div[3]/div/div[2]/span/a/span[3]")).click();
//        driver.findElement(By.xpath("//*[@id=\"ooui-894\"]/div/div/span/a/span[1]")).click();
//        driver.findElement(By.xpath("//*[@id=\"ooui-896\"]/span[3]")).click();
    }
}
