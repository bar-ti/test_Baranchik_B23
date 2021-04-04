import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AdminLoginFFNightlyTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        DesiredCapabilities caps;
        caps = DesiredCapabilities.firefox();
        caps.setCapability(FirefoxDriver.BINARY, "C:\\Program Files\\Firefox Nightly\\firefox.exe");
        driver = new FirefoxDriver(caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void adminLoginTest() throws InterruptedException {
        driver.navigate().to("http://127.0.0.1/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
