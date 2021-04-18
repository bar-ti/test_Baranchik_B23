import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class CreateNewUserTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Test
    public void browserChromeTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        createNewUser(driver);
    }

    @Test
    public void browserIETest() throws InterruptedException {
        System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");
        InternetExplorerOptions optionsIE = new InternetExplorerOptions();
        optionsIE.ignoreZoomSettings();
        driver = new InternetExplorerDriver(optionsIE);
        wait = new WebDriverWait(driver, 10);
        createNewUser(driver);
    }

    @Test
    public void browserFirefoxTest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        createNewUser(driver);
    }

    public void createNewUser(WebDriver driver) throws InterruptedException {
        driver.navigate().to("http://127.0.0.1/litecart");
        driver.findElement(By.xpath("//a[contains(text(),'New customers click here')]")).click();
        sleep(1000);
        driver.findElement(By.name("firstname")).sendKeys("New");
        driver.findElement(By.name("lastname")).sendKeys("User");
        driver.findElement(By.name("address1")).sendKeys("user address");
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys("user city");
        driver.findElement(By.xpath("//span[@class='select2-selection select2-selection--single']")).click();
        driver.findElement(By.xpath("//input[@class='select2-search__field']")).sendKeys("United States");
        driver.findElement(By.xpath("//li[text()='United States']")).click();
        int random_number = 1 + (int) (Math.random() * 100);
        driver.findElement(By.name("email")).sendKeys("test" + random_number + "@test.ru");
        driver.findElement(By.name("phone")).sendKeys("+10000000000");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("confirmed_password")).sendKeys("123");
        driver.findElement(By.name("create_account")).click();
        sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.findElement(By.name("email")).sendKeys("test" + String.valueOf(random_number) + "@test.ru");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("login")).click();
        sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
