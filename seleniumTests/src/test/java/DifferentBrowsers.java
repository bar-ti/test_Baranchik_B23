import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class DifferentBrowsers {

    public WebDriver driverChrome;
    public WebDriverWait waitChrome;
    public WebDriver driverIE;
    public WebDriverWait waitIE;
    public WebDriver driverFirefox;
    public WebDriverWait waitFirefox;

    @Before
    public void start() {
        ChromeOptions optionsChrome = new ChromeOptions();
        optionsChrome.addArguments("start-maximized");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driverChrome = new ChromeDriver();
        waitChrome = new WebDriverWait(driverChrome, 10);

        System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");
        InternetExplorerOptions optionsIE = new InternetExplorerOptions();
        optionsIE.ignoreZoomSettings();
        driverIE = new InternetExplorerDriver(optionsIE);
        waitIE = new WebDriverWait(driverIE, 10);

        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        driverFirefox = new FirefoxDriver();
        waitFirefox = new WebDriverWait(driverFirefox, 10);
    }

    @Test
    public void diffBrowsersTest() {
        driverChrome.navigate().to("http://127.0.0.1/litecart/admin");
        driverChrome.findElement(By.name("username")).sendKeys("admin");
        driverChrome.findElement(By.name("password")).sendKeys("admin");
        driverChrome.findElement(By.name("login")).click();
        waitChrome.until(titleIs("My Store"));

        driverIE.navigate().to("http://127.0.0.1/litecart/admin");
        driverIE.findElement(By.name("username")).sendKeys("admin");
        driverIE.findElement(By.name("password")).sendKeys("admin");
        driverIE.findElement(By.name("login")).click();
        waitIE.until(titleIs("My Store"));

        driverFirefox.navigate().to("http://127.0.0.1/litecart/admin");
        driverFirefox.findElement(By.name("username")).sendKeys("admin");
        driverFirefox.findElement(By.name("password")).sendKeys("admin");
        driverFirefox.findElement(By.name("login")).click();
        waitFirefox.until(titleIs("My Store"));
    }

    @After
    public void stop() {
        driverChrome.quit();
        driverChrome = null;
        driverIE.quit();
        driverIE = null;
        driverFirefox.quit();
        driverFirefox = null;
    }
}
