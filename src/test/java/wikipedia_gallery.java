import org.openqa.selenium.By;
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

public class wikipedia_gallery extends wikipedia_start{
    int counter = 0;
    //setups
    @Test(priority = 0)
    void picture() throws InterruptedException {
        //main page
        driver.get("https://www.wikipedia.org/");
        driver.manage().window().maximize();
        //go to English
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("link-box"))).click();
        //today page picture
        driver.findElement(By.xpath("//*[@id=\"mp-tfa-img\"]/div/span/a/img")).click();
        Thread.sleep(2000);
        //scroll through images
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mw-mmv-next-image"))).click();
        Thread.sleep(2000);
        driver.findElement(By.className("mw-mmv-prev-image")).click();
        Thread.sleep(2000);
//        //share
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[6]/div/div[1]/a[2]"))).click();
//        Thread.sleep(2000);
//        String shareLink = driver.findElement(By.id("ooui-2")).getText();
//        System.out.println("Share link: " + shareLink);
//        String actualShareLink = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/a[2]")).getAttribute("href");
//        Assert.assertTrue(actualShareLink == shareLink, "Read only text");
    }
    @Test(priority = 1)
    void gallery_options() throws InterruptedException {
        //fullscreen
        driver.findElement(By.className("mw-mmv-fullscreen")).click();
        driver.findElement(By.className("mw-mmv-fullscreen")).click();
        //media viewer
        driver.findElement(By.className("mw-mmv-options-button")).click();
        //close picture
        driver.findElement(By.className("mw-mmv-close")).click();
    }
}
