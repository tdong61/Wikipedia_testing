
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class wikipedia_tools extends wikipedia_start{
    int counter = 0;
    @Test(priority = 0)
    void tools() throws  InterruptedException{
        //setup from wikipedia page and maximize
        driver.get("https://www.wikipedia.org/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        //go to English
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("link-box"))).click();
        Thread.sleep(2000);
        //click on tools dropdown
        driver.findElement(By.id("vector-page-tools-dropdown-checkbox")).click();
        Thread.sleep(2000);
        //get permanent link
        driver.findElement(By.id("t-permalink")).click();
        System.out.println("Permanent link: " + driver.getCurrentUrl());
        Thread.sleep(2000);
        //click on tools dropdown
        driver.findElement(By.id("vector-page-tools-dropdown-checkbox")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("t-info")).click();
        WebElement basicInfo = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/table[1]"));
        System.out.println("Basic Inforomation: " + basicInfo.getText());
        WebElement editHistory = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/table[3]"));
        System.out.println("Edit History: " + editHistory.getText());
    }
//    @AfterTest
//    void home() throws InterruptedException{
//        driver.findElement(By.className("mw-logo-tagline")).click();
//        Thread.sleep(2000);
//    }
    @Test(priority = 1)
    void correct_login() throws InterruptedException {
        driver.findElement(By.className("mw-logo-tagline")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("pt-login-2")).click();
        // enter username and passowrd
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("wpName1")))).sendKeys("Tdtddkk3123s");
        driver.findElement(By.id("wpPassword1")).sendKeys("asdfe2znna");
        // check to keep login
        driver.findElement(By.id("wpRemember")).click();
        // login
        driver.findElement(By.id("wpLoginAttempt")).click();
        Thread.sleep(2000);
    }
    @Test(priority = 2)
    void logout() throws InterruptedException {
        //logout
        driver.findElement(By.xpath("//*[@id=\"vector-user-links-dropdown-checkbox\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"pt-logout\"]/a/span[2]")).click();
        //try to login but help with logging in
        driver.findElement(By.className("mw-logo-tagline")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("pt-login-2")).click();
        //try to login but forgot password
        driver.findElement(By.xpath("//*[@id=\"userloginForm\"]/form/div[6]/div/a")).click();
        //username autofill but email needed to reset
        driver.findElement(By.name("wpEmail")).sendKeys("testtony406@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"ooui-php-6\"]/button")).click();
        String title = "Reset password - Wikipedia";
        Assert.assertEquals(title, driver.getTitle(), "Reset Password Failure");
    }
    @Test(priority = 3)
    void register() throws InterruptedException {
        //get to login page to register
        String title = "Welcome, Tdtddkk3123s2! - Wikipedia";
        driver.findElement(By.className("mw-logo-tagline")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("pt-login-2")).click();
        //button to register
        driver.findElement(By.id("mw-createaccount-join")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpName2"))).sendKeys("Tdtddkk3123s");
        driver.findElement(By.id("wpPassword2")).sendKeys("asdfe2znna");
        driver.findElement(By.id("wpRetype")).sendKeys("asdfe2znna");
        driver.findElement(By.id("wpEmail")).sendKeys("testtony406@gmail.com");
        driver.findElement(By.name("captchaWord")).sendKeys("aaaaa");
        Thread.sleep(3000);
        //this will fail due to captcha and username is taken
        Assert.assertTrue(title == driver.getTitle(), "Registration failed");
    }
    @Test(priority = 4)
    void profile() throws  InterruptedException{
        driver.findElement(By.className("mw-logo-tagline")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("pt-login-2")).click();
        // enter username and passowrd, username not necessary
        //wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("wpName1")))).sendKeys("Tdtddkk3123s");
        driver.findElement(By.id("wpPassword1")).sendKeys("asdfe2znna");
        // check to keep login
        driver.findElement(By.id("wpRemember")).click();
        // login
        driver.findElement(By.id("wpLoginAttempt")).click();
        Thread.sleep(2000);
        //profile homepage
        driver.findElement(By.id("pt-userpage-2")).click();
        Thread.sleep(2000);
        //process to find articles to edit, select topics
        WebElement selectedTopics = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/div[1]/div[2]/div/div[1]/div/div[1]/span/span[1]/a/span[2]")));
        selectedTopics.click();
        Thread.sleep(2000);
        //filter Art and done, uncheck before starting
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/div[2]/div")).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/div/div[1]/span/a/span[2]")))).click();
        //filter to and amount of articles
        driver.findElement(By.className("oo-ui-processDialog-actions-primary")).click();
        WebElement articles = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[3]/div[2]/div/label"));
        System.out.println("Number of articles: " + articles.getText());
        driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/div/div[1]/span/a")).click();
        //filter through suggestions next and back and description
        driver.findElement(By.className("suggested-edits-next")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("suggested-edits-previous")).click();
        WebElement traffic = driver.findElement(By.className("se-card-pageviews"));
        System.out.println("Traffic: " + traffic.getText());
        WebElement difficulty = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/div[1]/div[2]/div/div[1]/div/div[4]/div/div/div[2]/div/div[1]"));
        WebElement description = driver.findElement(By.className("suggested-edits-short-description"));
        System.out.println("Difficulty: " + difficulty.getText());
        System.out.println("Description: " + description.getText());
        Thread.sleep(2000);
        driver.findElement(By.className("suggested-edits-task-card-wrapper")).click();
        Thread.sleep(2000);
    }
    @Test(priority = 5)
    void preferences_check() throws InterruptedException {
        //alerts->preferences
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pt-notifications-alert"))).click();
        Thread.sleep(2000);
        //alerts and stop short of Notifications tab by going through tabs
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/div[1]/div[3]/div/span/span[2]/a/span[2]")).click();
        counter = 1;
        for (int i = 0; i < 8; i++) {
            try {
                driver.findElement(By.id("ooui-php-50" + counter)).click();
                Thread.sleep(2000);
                counter++;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Stale Element");
            }
        }
        counter = 0;
        List<WebElement> check = driver.findElements(By.className(("oo-ui-inputWidget-input")));
        for (WebElement element : check) {
            try {
                element.click();
                counter++;
                Thread.sleep(1000);
            } catch (org.openqa.selenium.ElementNotInteractableException e) {
                System.out.println("Element not interactable.");
            } catch (org.openqa.selenium.StaleElementReferenceException f) {
                System.out.println("Stale Element.");
            }
        }
        //count buttons clicked
        Assert.assertFalse(check.size() == counter, "Total number of interactive buttons: " + counter);
        Thread.sleep(2000);
    }
    @Test(priority = 6)
    void preferences_shortened_url() throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(("vector-page-tools-landmark")))).click();
        //shortened url get
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("t-urlshortener"))).click();
        //copy url to clipboard
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mw-teleport-target\"]/div[2]/div/div[1]/div[2]/div[2]/div/div/label[2]/div/div/div/span/span/a/span[2]"))).click();
        //another way to copy
        WebElement url = driver.findElement(By.id("ooui-6"));
        String shortUrl = url.getAttribute("value");
        System.out.println("Shortened Url: " + url.getAttribute("value"));
        //exit
        driver.findElement(By.xpath("//*[@id=\"mw-teleport-target\"]/div[2]/div/div[1]/div[2]/div[3]/div/span/a")).click();
        String currentUrl = driver.getCurrentUrl();
        Thread.sleep(1000);
        //switch to new tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(shortUrl);
        Thread.sleep(10000);
        String newUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, newUrl, "Shorterned url doesn't work: " + shortUrl);
        driver.close();
        driver.switchTo().window(current);
    }
}