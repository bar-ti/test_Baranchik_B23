package ShoppingCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='box-most-popular']//ul/li")
    public WebElement boxMostPopular;

    public MainPage open() {
        driver.get("http://127.0.0.1/litecart");
        return this;
    }

    public void waitBoxMostPopularAndClick() {
        wait.until(elementToBeClickable(boxMostPopular));
        boxMostPopular.click();
    }

    public void returnToMainPage() {
        driver.findElement(By.id("logotype-wrapper")).click();
    }

    public void checkoutClick() {
        driver.findElement(By.xpath("//a[contains(text(),'Checkout')]")).click();
    }
}
