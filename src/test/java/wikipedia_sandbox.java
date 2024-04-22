import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class wikipedia_sandbox extends wikipedia_start{
    int counter = 0;
    @Test(priority = 0)
    void start_sandbox() throws InterruptedException{
        //setup from wikipedia page
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.manage().window().maximize();
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
        //sandbox
        driver.findElement(By.id("vector-user-links-dropdown-checkbox")).click();
        driver.findElement(By.xpath("//*[@id=\"pt-sandbox\"]/a/span[2]")).click();
    }
    @Test(priority = 1)
    void user_page() throws InterruptedException {
        //edit source
        driver.findElement(By.xpath("//*[@id=\"ca-edit\"]/a/span")).click();
        //write on textbox
        driver.findElement(By.id("wpTextbox1")).sendKeys("This is my userpage.");
        //insert special character from Insert
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"editpage-specialchars\"]/select"))).click();
        driver.findElement(By.xpath("//*[@id=\"editpage-specialchars\"]/select/option[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Insert\"]/a[1]")));
        //dropdown of options ->math logic
        driver.findElement(By.xpath("//*[@id=\"editpage-specialchars\"]/select")).click();
        driver.findElement(By.xpath("//*[@id=\"editpage-specialchars\"]/select/option[11]")).click();
        driver.findElement(By.xpath("//*[@id=\"Math and logic\"]/a[2]")).click();
        driver.findElement(By.id("wpSummary")).sendKeys("Summary");
        //uncheck watchpage
        driver.findElement(By.xpath("//*[@id=\"mw-editpage-watch\"]/div/span[2]/label")).click();
        //show preview
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpPreview"))).click();
        //publish
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpSave"))).click();
        String edit = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p")).getText();
        System.out.println("Edit: " + edit);
        //edit source again
        driver.findElement(By.xpath("//*[@id=\"ca-edit\"]/a/span")).click();
        //rewrite textbox
        driver.findElement(By.id("wpTextbox1")).sendKeys("AAAAAAAAA");
        //show diff
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpDiff"))).click();
        //publish again
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpSave"))).click();
        String updateEdit = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/p")).getText();
        Assert.assertTrue(edit == updateEdit, "Saved Edits: " + updateEdit);
    }
    @Test(priority = 2)
    void revision_history() throws InterruptedException {
        String message = "No matching revisions were found.";
        //check history
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//*[@id=\"ca-history\"]/a/span")))).click();
        //check each change from userpage
        List<WebElement> version = driver.findElements(By.className("mw-changeslist-date"));
        for(WebElement element : version) {
            try {
                System.out.println("Revision: " + element.getText());
                element.click();
                Thread.sleep(200);
                driver.navigate().back();
            }
            catch (org.openqa.selenium.StaleElementReferenceException e) {
                System.out.println("Stale Element");
            }
        }
        //filter revisions
        driver.findElement(By.xpath("//*[@id=\"mw-history-search\"]/legend/span[3]")).click();
        //filter no revisions
        driver.findElement(By.xpath("//*[@id=\"tagfilter\"]/div/span/a/span[3]")).click();
        //advanced mobile edit
        WebElement box = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tagfilter\"]/div/span/a")));
        box.click();
        box.sendKeys("2017 wikitext editor");
        box.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//*[@id=\"ooui-php-9\"]/button")).click();
        String noRMessage = driver.findElement(By.id("pagehistory")).getText();
        Assert.assertEquals(message,noRMessage, "There is a change: " + noRMessage);
        Thread.sleep(2000);
    }
}
