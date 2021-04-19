import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ShoppingCartTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Test
    public void browserChromeTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        checkShoppingCart(driver);
    }

    @Test
    public void browserIETest() throws InterruptedException {
        System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");
        InternetExplorerOptions optionsIE = new InternetExplorerOptions();
        optionsIE.ignoreZoomSettings();
        driver = new InternetExplorerDriver(optionsIE);
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        checkShoppingCart(driver);
    }

    @Test
    public void browserFirefoxTest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        checkShoppingCart(driver);
    }

    public void checkShoppingCart(WebDriver driver) throws InterruptedException {
        driver.navigate().to("http://127.0.0.1/litecart");
        for (int i = 0; i < 5; i++) {
            addProductToCart(driver);
            driver.findElement(By.id("logotype-wrapper")).click();
        }
        sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
        for (int i = 0; i < 5; i++) {
            deleteDuck(driver);
        }

    }

    public void addProductToCart(WebDriver driver) throws InterruptedException {
        wait.until(elementToBeClickable(driver.findElement(By.xpath("//div[@id='box-most-popular']//ul/li"))));
        sleep(1000);
        driver.findElement(By.xpath("//div[@id='box-most-popular']//ul/li")).click();

        Integer counter = Integer.parseInt(driver.findElement(By.className("quantity")).getText());
        if (driver.findElements(By.name("options[Size]")).size() > 0) {
            Select select = new Select(driver.findElement(By.xpath("//select[@name='options[Size]']")));
            select.selectByVisibleText("Small");
        }
        wait.until(elementToBeClickable(driver.findElement(By.name("add_cart_product"))));
        driver.findElement(By.name("add_cart_product")).click();
        counter++;
        wait.until(visibilityOf(driver.findElement(By.xpath("//span[@class='quantity' and text()=" + counter + "]"))));
    }

    public void deleteDuck(WebDriver driver) throws InterruptedException {
        int rowCount = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr")).size();
        if (rowCount > 5) {
            WebElement table = driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']"));
            if (rowCount > 6)
                driver.findElement(By.xpath("//ul[@class='shortcuts']/li/a")).click();
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(table));
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}