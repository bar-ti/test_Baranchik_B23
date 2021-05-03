package ShoppingCart;

import ShoppingCart.app.Application;
import org.junit.After;
import org.junit.Test;

public class ShoppingCartObjectTest {

    public Application app;

    @Test
    public void browserFirefoxTest() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/driver/geckodriver.exe");
        int productsCount = 3;
        app = new Application();
        app.open();
        app.addProductToCart(productsCount);
        app.goToShoppingCart();
        app.deleteProductFromCart(productsCount);

    }
    @After
    public void stop() {
        app.quit();
     }
}