import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckStickerTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void checkStickersTest()  {
        driver.navigate().to("http://127.0.0.1/litecart");
        List<WebElement> products = driver.findElements(By.xpath("//div[@id='box-most-popular']//li/a[@class='link']"));
        List<String> productsList = new ArrayList<>();
        for (WebElement product : products) {
            productsList.add(product.getAttribute("href"));
        }
       for (String productLink : productsList) {
           Assert.assertEquals(driver.findElements(By.xpath("//div[@id='box-most-popular']//a[@href='"+productLink+"']//div[contains(@class,'sticker')]")).size(),1);
           Assert.assertTrue(driver.findElement(By.xpath("//div[@id='box-most-popular']//a[@href='"+productLink+"']//div[contains(@class,'sticker')]")).isDisplayed());
         }
        products = driver.findElements(By.xpath("//div[@id='box-campaigns']//li/a[@class='link']"));
        productsList = new ArrayList<>();
        for (WebElement product : products) {
            productsList.add(product.getAttribute("href"));
        }
        for (String productLink : productsList) {
            Assert.assertEquals(driver.findElements(By.xpath("//div[@id='box-most-popular']//a[@href='"+productLink+"']//div[contains(@class,'sticker')]")).size(),1);
            Assert.assertTrue(driver.findElement(By.xpath("//div[@id='box-most-popular']//a[@href='"+productLink+"']//div[contains(@class,'sticker')]")).isDisplayed());
        }
        products = driver.findElements(By.xpath("//div[@id='box-latest-products']//li/a[@class='link']"));
        productsList = new ArrayList<>();
        for (WebElement product : products) {
            productsList.add(product.getAttribute("href"));
        }
        for (String productLink : productsList) {
            Assert.assertEquals(driver.findElements(By.xpath("//div[@id='box-most-popular']//a[@href='"+productLink+"']//div[contains(@class,'sticker')]")).size(),1);
            Assert.assertTrue(driver.findElement(By.xpath("//div[@id='box-most-popular']//a[@href='"+productLink+"']//div[contains(@class,'sticker')]")).isDisplayed());
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
