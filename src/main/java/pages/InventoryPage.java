package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
 
import java.util.List;
 
public class InventoryPage extends BasePage {
 
    private final By pageTitle = By.cssSelector(".title");
    private final By inventoryItems = By.cssSelector(".inventory_item");
 
    private final By sortDropdown =
            By.cssSelector("[data-test='product-sort-container']");
 
    private final By priceLabels = By.cssSelector(".inventory_item_price");
    private final By addToCartBtns = By.cssSelector("[data-test^='add-to-cart']");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartIcon = By.id("shopping_cart_container");
    private final By productNames = By.cssSelector(".inventory_item_name");
 
    public InventoryPage(WebDriver driver) {
        super(driver);
    }
 
    public boolean isLoaded() {
        try {
            return wait.until(ExpectedConditions.urlContains("/inventory"));
        } catch (TimeoutException e) {
            return false;
        }
    }
 
    public String getTitle() {
        return getText(pageTitle);
    }
 
    public int getProductCount() {
        return findAll(inventoryItems).size();
    }
 
    public void sortBy(String visibleText) {
        Select dropdown = new Select(waitForElement(sortDropdown));
        dropdown.selectByVisibleText(visibleText);
    }
 
    public void addToCart(int productIndex) {
        List<WebElement> items = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItems)
        );

        WebElement addBtn = items.get(productIndex)
            .findElement(addToCartBtns);

        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
    }
 
    public int getCartCount() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty() || !badges.get(0).isDisplayed()) return 0;
        return Integer.parseInt(badges.get(0).getText());
    }
 
    public void openCart() {
        click(cartIcon);
    }
 
    public List<WebElement> getPriceElements() {
        return findAll(priceLabels);
    }
 
    public List<WebElement> getNameElements() {
        return findAll(productNames);
    }
}