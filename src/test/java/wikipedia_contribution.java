import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class wikipedia_contribution extends wikipedia_start{
    int counter = 0;
    @Test(priority = 0)
    void contribution_setup() throws InterruptedException{
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
        //contribution
        driver.findElement(By.id("vector-user-links-dropdown-checkbox")).click();
        driver.findElement(By.xpath("//*[@id=\"pt-mycontris\"]/a/span[2]")).click();
        //prints for user
        System.out.println(driver.findElement(By.className("mw-contributions-editor-info")).getText());
    }
    @Test(priority = 1)
    void contribution_buttons() throws InterruptedException {
        //dropdown
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/form/fieldset/legend/span[3]"))).click();
        List<WebElement> checkbox = driver.findElements(By.className("oo-ui-inputWidget-input"));
        List<WebElement> date = driver.findElements(By.className("mw-widget-dateInputWidget-handle"));
        //input dates attempt
        for(WebElement element : checkbox) {
            try {
                String placeholder = element.getAttribute("placeholder");
                if(placeholder != null && element.getAttribute("placeholder").equals("YYYY-MM-DD")) {
                    element.click();
                    element.sendKeys("2024-04-10");
                    Thread.sleep(1000);
                    System.out.println("Entered Date");
                }
                else {
                    Thread.sleep(1000);
                    element.click();
                }
            }
            catch (org.openqa.selenium.ElementNotInteractableException e){
                System.out.println("Element not interactable");
            }
        }
    }
    @Test(priority = 2)
    void check_contributions() throws InterruptedException{
        String correct = "No changes were found matching these criteria.";
        WebElement updates = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id=\"mw-content-text\"]/p")));
        updates.getText();
        Assert.assertEquals(correct,updates.getText(), "There is a change: " + updates.getText());
    }
    @Test(priority = 3)
    void atom() throws InterruptedException {
        //tools->atom
        Thread.sleep(2000);
        WebElement tool = driver.findElement(By.id("vector-page-tools-dropdown-checkbox"));
        tool.click();
        driver.findElement(By.id("feed-atom")).click();
        Thread.sleep(2000);
        System.out.println("XML: " + driver.findElement(By.id("folder0")).getText());
        Thread.sleep(2000);
        driver.navigate().back();
    }
    @Test(priority = 4)
    void edit_count() throws InterruptedException {
        //contribution->edit coiutn
        String currentUrl = driver.getWindowHandle();
        WebElement editCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"sp-contributions-footer\"]/tbody/tr/td[2]/div/ul/li[3]/a")));
        String url = editCount.getAttribute("href");
        Thread.sleep(2000);
        //switch to new tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);
        List<WebElement> hide = driver.findElements(By.className("xt-hide"));
        List<WebElement> show = driver.findElements(By.className("xt-show"));
        for(WebElement element : hide) {
            try {
                actions.scrollToElement(element).perform();
                Thread.sleep(2000);
                element.click();
            }
            catch (org.openqa.selenium.JavascriptException e) {
                System.out.println("N/A");
            }
            catch (org.openqa.selenium.NoSuchElementException f) {
                System.out.println("Element not found");
            }
        }
        for(WebElement element : show) {
            try {
                actions.scrollToElement(element).perform();
                Thread.sleep(2000);
                element.click();
            }
            catch (org.openqa.selenium.JavascriptException e) {
                System.out.println("N/A");
            }
        }
        //go back
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(currentUrl);
    }
}
