import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static java.lang.Thread.sleep;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AddNewProductTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Test
    public void browserChromeTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        addNewProduct(driver);
    }

    @Test
    public void browserIETest() throws InterruptedException {
        System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");
        InternetExplorerOptions optionsIE = new InternetExplorerOptions();
        optionsIE.ignoreZoomSettings();
        driver = new InternetExplorerDriver(optionsIE);
        wait = new WebDriverWait(driver, 10);
        addNewProduct(driver);
    }

    @Test
    public void browserFirefoxTest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        addNewProduct(driver);
    }

    public void addNewProduct(WebDriver driver) throws InterruptedException {
        driver.navigate().to("http://127.0.0.1/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
        sleep(500);
        driver.findElement(By.xpath("//*[text()='Catalog']")).click();
        sleep(500);
        driver.findElement(By.xpath("//a[text()=' Add New Product']")).click();
        sleep(500);
        driver.findElement(By.xpath("//label[text()=' Enabled']/input")).click();
        sleep(500);
        driver.findElement(By.name("name[en]")).sendKeys("testDuck");
        driver.findElement(By.name("code")).sendKeys("testCode");
        driver.findElement(By.xpath("//strong[text()='Product Groups']/parent::td//*[text()='Unisex']/parent::tr//input")).click();
        File file = new File("src\\test\\resources\\img\\PNG format.png");
        driver.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());
        driver.findElement(By.name("date_valid_from")).sendKeys("13.04.2021");
        driver.findElement(By.name("date_valid_to")).sendKeys("13.04.2022");
        sleep(500);
        driver.findElement(By.xpath("//a[text()='Information']")).click();
        sleep(500);
        driver.findElement(By.name("keywords")).sendKeys("keywords");
        driver.findElement(By.name("short_description[en]")).sendKeys("short description");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("description");
        driver.findElement(By.name("head_title[en]")).sendKeys("head title");
        driver.findElement(By.name("meta_description[en]")).sendKeys("meta description");
        sleep(500);
        driver.findElement(By.xpath("//a[text()='Prices']")).click();
        sleep(500);
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("10");
        Select select = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        select.selectByVisibleText("US Dollars");
        driver.findElement(By.name("gross_prices[USD]")).clear();
        driver.findElement(By.name("gross_prices[USD]")).sendKeys("10");
        driver.findElement(By.name("gross_prices[EUR]")).clear();
        driver.findElement(By.name("gross_prices[EUR]")).sendKeys("10");
        sleep(500);
        driver.findElement(By.name("save")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
