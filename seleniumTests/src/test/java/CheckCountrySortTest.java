import com.google.common.collect.Ordering;
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CheckCountrySortTest {

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
    public void checkCountrySortTest() {
        driver.navigate().to("http://127.0.0.1/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> countries = driver.findElements(By.xpath("//tr[@class='row']"));
        List<String> countriesList = new ArrayList<>();
        List<String> countriesWithZoneList = new ArrayList<>();
        for (WebElement tableRow : countries) {
            List<String> tmp = Arrays.asList(tableRow.getText().split(" "));
            String s = "";
            if (tmp.size() >= 4) {
                for (int i = 2; i < tmp.size() - 1; i++) {
                    s += tmp.get(i) + " ";
                }
            }
            countriesList.add(s);
            if (Integer.parseInt(tmp.get(tmp.size() - 1)) > 0)
                countriesWithZoneList.add(s.trim());
        }
        Assert.assertTrue(Ordering.natural().isOrdered(countriesList));
        List<String> zonesList = new ArrayList<>();
        for (String countryWithZones : countriesWithZoneList) {
            driver.findElement(By.xpath("//tr[@class='row']//a[contains(text(), '" + countryWithZones + "')]")).click();
            List<WebElement> zones = driver.findElements(By.xpath("//*[contains(@name,'][name]')]"));
            for (WebElement zone : zones) {
                zonesList.add(zone.getAttribute("value"));
            }
            Assert.assertTrue(Ordering.natural().isOrdered(zonesList));
            zonesList.clear();
            driver.navigate().back();
        }
    }

    @Test
    public void checkGeoZoneSortTest() {
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        List<WebElement> countries = driver.findElements(By.xpath("//tr[@class='row']"));
        List<String> countriesList = new ArrayList<>();
        for (WebElement tableRow : countries) {
            List<String> tmp = Arrays.asList(tableRow.getText().split(" "));
            String s = "";
            if (tmp.size() >= 3) {
                for (int i = 1; i < tmp.size() - 1; i++) {
                    s += tmp.get(i) + " ";
                }
            }
            countriesList.add(s.trim());
        }
        List<String> zonesList = new ArrayList<>();
        for (String country : countriesList) {
            wait.until(presenceOfElementLocated(By.xpath("//tr[@class='row']//a[contains(text(), '" + country + "')]"))).click();
            List<WebElement> zones = driver.findElements(By.xpath("//*[contains(@name,'[zone_code]')]/option[@selected='selected']"));
            for (WebElement zone : zones) {
                zonesList.add(zone.getText());
            }
            Assert.assertTrue(Ordering.natural().isOrdered(zonesList));
            zonesList.clear();
            driver.navigate().back();
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
