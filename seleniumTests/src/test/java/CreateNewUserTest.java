import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class CreateNewUserTest {

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
    public void checkStickersTest() throws InterruptedException {
        driver.navigate().to("http://127.0.0.1/litecart");
        driver.findElement(By.xpath("//a[contains(text(),'New customers click here')]")).click();
        sleep(1000);
        driver.findElement(By.name("firstname")).sendKeys("New");
        driver.findElement(By.name("lastname")).sendKeys("User");
        driver.findElement(By.name("address1")).sendKeys("user address");
        driver.findElement(By.name("postcode")).sendKeys("11111-1111");
        driver.findElement(By.name("city")).sendKeys("user city");
        Select select = new Select(driver.findElement(By.xpath("//select[@name='country_code']")));
        select.selectByVisibleText("United States");
        int random_number = 1 + (int) (Math.random() * 100);
        driver.findElement(By.name("email")).sendKeys("test"+String.valueOf(random_number)+"@test.ru");
        driver.findElement(By.name("phone")).sendKeys("+10000000000");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("confirmed_password")).sendKeys("123");
        driver.findElement(By.name("create_account")).click();
        sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
        driver.findElement(By.name("email")).sendKeys("test"+String.valueOf(random_number)+"@test.ru");
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
