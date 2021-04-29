import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CheckBrowserLogTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkBrowserLogTest() throws InterruptedException {
        driver.navigate().to("http://127.0.0.1/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<String> ducksLinksList = new ArrayList<>();
        List<WebElement> ducks = driver.findElements(By.xpath("//table[@class='dataTable']//td/a[contains(text(),'Duck')]"));
        for (WebElement duck : ducks) {
            ducksLinksList.add(duck.getAttribute("href"));
        }
        for (String link : ducksLinksList) {
            driver.findElement(By.xpath("//a[@href='" + link + "']")).click();
            wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Edit Product')]")));
            driver.navigate().back();
            wait.until(visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Catalog')]")));
        }
        for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
            Assert.assertEquals(l,"");
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
