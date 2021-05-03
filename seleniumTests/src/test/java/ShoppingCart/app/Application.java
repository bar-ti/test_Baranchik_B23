package ShoppingCart.app;

import ShoppingCart.pages.CartPage;
import ShoppingCart.pages.MainPage;
import ShoppingCart.pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Application {

    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        driver = new FirefoxDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void open() {
        mainPage.open();
    }

    public void quit() {
        driver.quit();
        driver = null;
    }

    public void addProductToCart(int productsCount) {

        for (int i = 0; i < productsCount; i++) {
            mainPage.waitBoxMostPopularAndClick();
            Integer counter = Integer.parseInt(ProductPage.quantityOfProductsInCart.getText());
            if (ProductPage.isProductHasSize(driver)) {
                ProductPage.selectProductSize("Small");
            }
            ProductPage.waitClicableBtnAddCartProductAndClick();
            counter++;
            ProductPage.waitCartQuantityWillIncrease(counter);
            mainPage.returnToMainPage();
        }
    }

    public void goToShoppingCart() {
        mainPage.checkoutClick();
    }

    public void deleteProductFromCart(int productsCount) {
        for (int i = 0; i < productsCount; i++) {
            int rowCount = CartPage.getProductsListTableRowCount(driver);
            if (rowCount > 5) {
                WebElement table = CartPage.productsListTable(driver);
                if (rowCount > 6)
                    CartPage.productsSliderElement.click();
                CartPage.btnRemoveCartItem.click();
                CartPage.waitProductsListTableUpdate(table);
            }

        }
    }
}
