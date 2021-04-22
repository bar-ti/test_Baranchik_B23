import org.junit.After;
import org.junit.Assert;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class LinksInNewWindowTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Test
    public void browserChromeTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        checkLinksOpensInNewWindow(driver);
    }

    @Test
    public void browserIETest() throws InterruptedException {
        System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");
        InternetExplorerOptions optionsIE = new InternetExplorerOptions();
        optionsIE.ignoreZoomSettings();
        driver = new InternetExplorerDriver(optionsIE);
        wait = new WebDriverWait(driver, 5);
  //      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        checkLinksOpensInNewWindow(driver);
    }

    @Test
    public void browserFirefoxTest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        checkLinksOpensInNewWindow(driver);
    }

    public void checkLinksOpensInNewWindow(WebDriver driver) throws InterruptedException {
        driver.navigate().to("http://127.0.0.1/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        sleep(500);
        wait.until(elementToBeClickable(driver.findElement(By.xpath("//a[text()=' Add New Country']"))));
        driver.findElement(By.xpath("//a[text()=' Add New Country']")).click();

        ArrayList<String> tabs = new ArrayList<>();

        List<WebElement> linksElements = driver.findElements(By.xpath("//form//table//a[@target='_blank']"));
        List<String> links = new ArrayList<>();

        for (WebElement link : linksElements) {
            links.add(link.getAttribute("href"));
        }
        for (String link : links) {
            driver.findElement(By.xpath("//a[@href='" + link + "']")).click();
            while (tabs.size() < 2) {
                sleep(500);
                tabs.addAll(driver.getWindowHandles());
            }
            driver.switchTo().window(tabs.get(1));
            driver.close();
            driver.switchTo().window(tabs.get(0));
            tabs.clear();
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}