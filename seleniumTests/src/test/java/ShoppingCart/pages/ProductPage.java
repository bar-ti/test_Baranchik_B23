package ShoppingCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "quantity")
    public static WebElement quantityOfProductsInCart;

    @FindBy(xpath = "//select[@name='options[Size]']")
    public static WebElement selectSizeOfProduct;

    @FindBy(name = "add_cart_product")
    public static WebElement btnAddCartProduct;

    public static void waitCartQuantityWillIncrease(int count) {
        wait.until(visibilityOfElementLocated(By.xpath("//span[@class='quantity' and text()=" + count + "]")));
    }

    public static void waitClicableBtnAddCartProductAndClick() {
        wait.until(elementToBeClickable(ProductPage.btnAddCartProduct));
        btnAddCartProduct.click();
    }

    public static boolean isProductHasSize(WebDriver driver) {
        boolean b = false;
        if (driver.findElements(By.name("options[Size]")).size() > 0)
            b = true;
        return b;
    }

    public static void selectProductSize(String size) {
        Select select = new Select(ProductPage.selectSizeOfProduct);
        select.selectByVisibleText(size);
    }


}
