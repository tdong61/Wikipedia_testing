import com.beust.ah.A;
import com.sun.source.tree.AssertTree;
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
import org.testng.asserts.Assertion;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class wikipedia_today_article extends wikipedia_start{
    int counter = 0;
    //setups
    @Test(priority = 0)
    void article() throws InterruptedException {
        //article
        String match = "Use Australian English";
        driver.get("https://en.wikipedia.org/wiki/Telopea_speciosissima");
        driver.manage().window().maximize();
    }
    @Test(priority = 2)
    void article_header_buttons() throws InterruptedException {
        //cultivation sublist
        WebElement cultivationSublist= driver.findElement(By.xpath("//*[@id=\"toc-Cultivation\"]/button"));
        if (cultivationSublist.getAttribute("aria-expanded") == "true") {
            cultivationSublist.click();
            Thread.sleep(2000);
        }
        else {
            cultivationSublist.click();
            Thread.sleep(2000);
        }
        //contents buttons
        List<WebElement> headers = driver.findElements(By.className("vector-toc-text"));
        try {
            for (WebElement element : headers) {
                element.click();
                System.out.println("Button clicked: " + element.getText());
                Thread.sleep(2000);
                counter++;
            }
        }
        catch (org.openqa.selenium.ElementNotInteractableException e) {
                System.out.println("Element not interactable");
            }
        Assert.assertTrue(headers.size() == counter, "Number of interactable buttons: " + counter);
    }
    @Test(priority = 3)
    void citation() throws InterruptedException{
        //move to citation from tools from article
        driver.findElement(By.id("vector-page-tools-dropdown-checkbox")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("t-cite")).click();
        //*[@id="mw-content-text"]/div[1]/div[3]/p[1]
        //*[@id="mw-content-text"]/div[1]/div[3]/p[2]
//        WebElement header = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/div[3]"));
//        List<WebElement> subHeader = header.findElements(By.className("mw-headline"));
        for(int i = 1; i < 9; i++) {
            try {
                WebElement title = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/div[1]/div[2]/ul/li["+i+"]/a"));
                title.click();
                System.out.println("Title: " + title.getText());
                Thread.sleep(1000);
                WebElement cite = driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/div[3]/p["+i+"]"));
                System.out.println("Citation: " + cite.getText());
            }
            catch (org.openqa.selenium.ElementNotInteractableException e) {
                System.out.println("No interactive element");
            }
        }
        Thread.sleep(1000);
    }
    @Test(priority = 4)
    void search() throws InterruptedException{
        //search thaat flower
        WebElement search = driver.findElement(By.name("search"));
        search.sendKeys("Telopea speciosissima");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchform\"]/div/button"))).click();
    }
    @Test(priority = 5)
    void article_references() throws InterruptedException {
        //click on references part
        String tab = driver.getWindowHandle();
        WebElement references_1 = driver.findElement(By.id("cite_note-APNI2-1"));
        System.out.println("Reference 1: " + driver.findElement(By.id("cite_note-APNI2-1")).getText());
        //going to a link by new tab
        WebElement newSite = driver.findElement(By.xpath("//*[@id=\"cite_note-APNI2-1\"]/span[2]/cite/a[1]"));
        String site = newSite.getAttribute("href");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(site);
        Thread.sleep(2000);
        driver.close();
        driver.switchTo().window(tab);
    }
    @Test(priority = 6)
    void pdf_download() throws InterruptedException, IOException {
        //tool->download pdf
        driver.findElement(By.xpath("//*[@id=\"vector-page-tools-dropdown-checkbox\"]")).click();
        driver.findElement(By.id("coll-download-as-rl")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mw-content-text\"]/form/div/span/span/button"))).click();
//        Desktop.getDesktop().open(new File("C:\\Users\\HP\\Downloads\\Telopea_speciosissima.pdf"));
        Thread.sleep(2000);
        //main page
        driver.findElement(By.className("mw-logo-tagline")).click();
        Thread.sleep(2000);
    }
}
