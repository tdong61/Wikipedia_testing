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
import java.util.Scanner;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class wikipedia extends wikipedia_start{
    int counter = 0;
    @Test(priority = 0)
    void setup() throws InterruptedException{
        //setup from wikipedia page
        driver.get("https://www.wikipedia.org/");
        driver.manage().window().maximize();
        //go to English
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("link-box"))).click();
        // Find the main article image element
        WebElement featuredArticleImage = driver.findElement(By.className("mw-file-element"));

        // Get the image source URL
        String imageURL = featuredArticleImage.getAttribute("src");
        // Total articles in English
        WebElement totalArticles = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"articlecount\"]/a[1]")));
        String amount = totalArticles.getText();
        // Get the main article image caption
        driver.findElement(By.className("mw-file-element")).click();
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mw-mmv-title")));
        String title = name.getText();
        // Get main article image author
        WebElement from = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mw-mmv-author")));
        String author = from.getText();
        // Get copyright information
        WebElement copyright = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mw-mmv-license")));
        String info = copyright.getText();
        //Fullscreen image and return to original
        driver.findElement(By.className("mw-mmv-fullscreen")).click();
        //close picture
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mw-mmv-close"))).click();
        //in the news
        WebElement inTheNews = driver.findElement(By.id("mp-itn"));
        String news = inTheNews.getText();
        //did you know
        WebElement didYouKnow = driver.findElement(By.id("mp-dyk"));
        String know = didYouKnow.getText();
        //on this day
        WebElement onThisDay = driver.findElement(By.id("mp-otd"));
        String today = onThisDay.getText();
        //Monday or Friday with featured lists, other days none, so take input day or get day
        // Basic prints
        System.out.println("Today's Featured Article Image URL: " + imageURL);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Copyright: " + info);
        System.out.println("Total articles in English: " + amount);
        System.out.println("In the new: " + news);
        System.out.println("Did you know.. " + know);
        System.out.println("On this day: " + today);
        // drop down from main page and click current events
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vector-main-menu-dropdown"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id("n-currentevents")).click();
        // pin instead of drop down
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vector-main-menu-dropdown"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"vector-main-menu\"]/div[1]/button[1]"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"vector-main-menu\"]/div[1]/button[2]")).click();
        Thread.sleep(2000);
        // main page logo return home
        driver.findElement(By.className("mw-logo-tagline")).click();
        Thread.sleep(2000);
    }
    @Test(priority = 1)
    void source_template() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/September_11_attacks");
        try {
            driver.findElement(By.id("ca-viewsource")).click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return;
        }
        String text = driver.findElement(By.id("wpTextbox1")).getText();
        System.out.println("View/Source: " + text);
        if (text.contains("Good article")) {
            driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[3]/ul/li[71]/a[1]")).click();
            System.out.println("Title: " + driver.getTitle());
            WebElement template = driver.findElement(By.className("mw-templatedata-caption"));
            //just visual scroll
            actions.scrollToElement(template).perform();
            System.out.println("About: " + driver.findElement(By.xpath("//*[@id=\"mw-content-text\"]/div[1]/div/div[1]/p[1]")).getText());
            Thread.sleep(2000);
        }
        //return to main aricle
        driver.findElement(By.className("mw-logo-tagline")).click();
    }
    @Test(priority = 2)
    void language() throws InterruptedException {
        // Click on language button
        driver.findElement(By.id("p-lang-btn-checkbox")).click();
        Thread.sleep(2000);
        // german language find
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[1]/div/ul[2]/li[1]/a"))).click();
        Thread.sleep(2000);
        //use sidebar to search for English
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"p-lang\"]/div/ul/li[9]/a/span"))).click();
        // main page logo return home
        driver.findElement(By.className("mw-logo-tagline")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("p-lang-btn-checkbox")).click();
        Thread.sleep(2000);
        //search bar enter and clear
        driver.findElement(By.xpath("//*[@id=\"search\"]/div/div/input[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"search\"]/div/div/input[2]")).sendKeys("English");
        Thread.sleep(2000);
        driver.findElement(By.className("uls-languagefilter-clear")).click();
    }
    @Test(priority = 3)
    void currentEvents() throws InterruptedException{
        // drop down from main page and click current events
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vector-main-menu-dropdown"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id("n-currentevents")).click();
        String time_date = driver.findElement(By.className("p-current-events-calendar")).getText();
        System.out.println("Time and date: " + time_date);
        List<WebElement> hide = driver.findElements(By.className("mw-collapsible-text"));
        System.out.println("Number of button elements for mw-collapsible-text: " + hide.size());
        for(WebElement element : hide) {
            try {
                actions.scrollToElement(element);
                element.click();
                counter++;
                Thread.sleep(2000);
            }
            catch (org.openqa.selenium.ElementNotInteractableException e){
                System.out.println("Element not available.");
            }
        }
        // 8/9 button elements working, return false
        Assert.assertEquals(hide.size() == counter, "Number of interactive element of hide/show button: " + counter);
    }
    @Test(priority = 4)
    void close() throws InterruptedException{
        // main page logo return home
        driver.findElement(By.className("mw-logo-tagline")).click();
        Thread.sleep(2000);
    }
}

