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

import static java.lang.Thread.sleep;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ViewAllSectionsTest {

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
    public void adminLoginTest() throws InterruptedException {
        driver.navigate().to("http://127.0.0.1/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
        List<WebElement> menuItemsElements = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li/a"));
        List<String> menuItemsList = new ArrayList<>();
        for (WebElement element : menuItemsElements) {
            menuItemsList.add(element.getAttribute("href"));
        }
        for (String item : menuItemsList) {
            sleep(500);
            wait.until(presenceOfElementLocated(By.xpath("//a[@href='" + item + "']/parent::li[@id='app-']"))).click();
            Assert.assertTrue(driver.findElement(By.xpath("//h1")).isDisplayed());
            List<WebElement> menuSubItemsElements = driver.findElements(By.xpath("//a[@href='" + item + "']/parent::li[@id='app-']//li"));
            List<String> menuSubItemsList = new ArrayList<>();
            for (WebElement element : menuSubItemsElements) {
                menuSubItemsList.add(element.getAttribute("id"));
            }
            for (String subItem : menuSubItemsList) {
                driver.findElement(By.xpath("//*[@id='" + subItem + "']")).click();
                Assert.assertTrue(driver.findElement(By.xpath("//h1")).isDisplayed());
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
