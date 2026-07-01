package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
 
import java.util.List;
import java.util.stream.Collectors;
 
public class CartPage extends BasePage {
 
    private final By cartItems = By.cssSelector(".cart_item");
    private final By removeButtons = By.cssSelector("[data-test^='remove']");
    private final By checkoutBtn = By.id("checkout");
    private final By continueShoppingBtn = By.id("continue-shopping");
    private final By itemNames = By.cssSelector(".inventory_item_name");
 
    public CartPage(WebDriver driver) {
        super(driver);
    }
 
    public int getItemCount() {
        return findAll(cartItems).size();
    }
 
    public boolean isEmpty() {
        return driver.findElements(cartItems).isEmpty();
    }
 
    public void removeItem(int index) {
        List<WebElement> buttons = findAll(removeButtons);
        buttons.get(index).click();
    }
 
    public void goToCheckout() {
        wait.until(ExpectedConditions.urlContains("/cart"));
        click(checkoutBtn);
    }
 
    public void continueShopping() {
        click(continueShoppingBtn);
    }
 
    public List<String> getItemNames() {
        return findAll(itemNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}