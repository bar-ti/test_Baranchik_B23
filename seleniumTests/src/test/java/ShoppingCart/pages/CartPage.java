package ShoppingCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartPage extends Page {
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//ul[@class='shortcuts']/li/a")
    public static WebElement productsSliderElement;

    @FindBy(name="remove_cart_item")
    public static WebElement btnRemoveCartItem;

    public static WebElement  productsListTable(WebDriver driver) {
        return driver.findElement(By.xpath("//table[@class='dataTable rounded-corners']"));
    }
    public static int getProductsListTableRowCount(WebDriver driver) {
        return driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//tr")).size();
    }

    public static void waitProductsListTableUpdate(WebElement table) {
        wait.until(stalenessOf(table));
    }

}
