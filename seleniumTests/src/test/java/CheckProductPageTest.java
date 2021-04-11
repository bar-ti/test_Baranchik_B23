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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class CheckProductPageTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @Test
    public void browserChromeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        checkProductPage(driver);
    }

    @Test
    public void browserIETest() {
        System.setProperty("webdriver.ie.driver", "src/test/resources/driver/IEDriverServer.exe");
        InternetExplorerOptions optionsIE = new InternetExplorerOptions();
        optionsIE.ignoreZoomSettings();
        driver = new InternetExplorerDriver(optionsIE);
        wait = new WebDriverWait(driver, 10);
        checkProductPage(driver);
    }

    @Test
    public void browserFirefoxTest() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        checkProductPage(driver);
    }

    void checkProductPage(WebDriver driver) {
        driver.navigate().to("http://127.0.0.1/litecart");
        WebElement duck = driver.findElement(By.xpath("//div[@id='box-campaigns']//li/a[@class='link']"));
        String duckName = duck.getAttribute("title");
        WebElement duckRegPriceEl = driver.findElement(By.xpath("//div[@id='box-campaigns']//li/a[@class='link']//s[@class='regular-price']"));
        String duckRegPrice = duckRegPriceEl.getText();
        WebElement duckCamPriceEl = driver.findElement(By.xpath("//div[@id='box-campaigns']//li/a[@class='link']//strong[@class='campaign-price']"));
        String duckCamPrice = duckCamPriceEl.getText();
        checkPrice(duckRegPriceEl, duckCamPriceEl);
        duck.click();
        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), duckName);
        duckRegPriceEl = driver.findElement(By.xpath("//s[@class='regular-price']"));
        duckCamPriceEl = driver.findElement(By.xpath("//strong[@class='campaign-price']"));
        Assert.assertEquals(duckRegPriceEl.getText(), duckRegPrice);
        Assert.assertEquals(duckCamPriceEl.getText(), duckCamPrice);
        checkPrice(duckRegPriceEl, duckCamPriceEl);
    }

    void checkPrice(WebElement regPrice, WebElement camPrice) {
        String duckRegPriceColor = regPrice.getCssValue("color");
        List<String> duckPriceColor = Arrays.asList(duckRegPriceColor.substring(duckRegPriceColor.indexOf("(") + 1, duckRegPriceColor.lastIndexOf(")")).split(", "));
        String duckRegPriceFontSize = regPrice.getCssValue("font-size");
        String duckRegPriceTextDecoration = regPrice.getCssValue("text-decoration");

        Assert.assertTrue(duckRegPriceTextDecoration.contains("line-through"));
        Assert.assertTrue(duckPriceColor.get(0).equals(duckPriceColor.get(1)) & duckPriceColor.get(0).equals(duckPriceColor.get(2)));

        String duckCamPriceColor = camPrice.getCssValue("color");
        String duckCamPriceFontSize = camPrice.getCssValue("font-size");
        String duckCamPriceFontWeight = camPrice.getCssValue("font-weight");

        Assert.assertTrue(Integer.parseInt(duckCamPriceFontWeight) >= 700);
        duckPriceColor = Arrays.asList(duckCamPriceColor.substring(duckRegPriceColor.indexOf("(") + 1, duckCamPriceColor.lastIndexOf(")")).split(", "));
        Assert.assertTrue(duckPriceColor.get(1).equals("0") & duckPriceColor.get(2).equals("0"));

        Assert.assertTrue(Float.parseFloat(duckRegPriceFontSize.replace("px", "")) < Float.parseFloat(duckCamPriceFontSize.replace("px", "")));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
