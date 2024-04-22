import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class wikipedia_watchlist_cont  extends wikipedia_start{
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
        //watchlist
        driver.findElement(By.xpath("//*[@id=\"pt-watchlist-2\"]/a/span[1]")).click();
        Thread.sleep(2000);
        System.out.println("About: " +driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[2]/div/div/div[1]/div/div[1]/div/div[1]")).getText());
    }
    @Test(priority = 1)
    void view_edit_watchlist() throws InterruptedException {
        //watchlist->view/edit watchlist
        driver.findElement(By.xpath("//*[@id=\"ca-special-specialAssociatedNavigationLinks-link-1\"]/a")).click();
        //try remove
        try {
            driver.findElement(By.name("wpCheckAllNs0")).click();
            driver.findElement(By.xpath("//*[@id=\"ooui-php-8\"]/button/span[2]")).click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Nothing to delete");
        }
        System.out.println("Title: "+ driver.getTitle());

    }
    @Test(priority = 2)
    void edit_raw_watchlist() throws InterruptedException {
        //view-edit watchlist->edit raw watchlist
        driver.findElement(By.xpath("//*[@id=\"ca-special-specialAssociatedNavigationLinks-link-2\"]/a/span")).click();
        WebElement owner = driver.findElement(By.className("mw-watchlist-owner"));
        //put owner to watch
        String owner_string = owner.getText();
        String[] split = owner_string.split(" ");
        String need = split[1];
        driver.findElement(By.id("ooui-php-2")).sendKeys(need);
        //update
        driver.findElement(By.xpath("//*[@id=\"ooui-php-4\"]/button")).click();
        Thread.sleep(1000);
    }
}
