package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartTest extends BaseTest {
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeMethod
    public void init() {
        LoginPage loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Cart should be empty on fresh login")
    public void testCartIsEmptyInitially() {
        inventoryPage.openCart();
        Assert.assertTrue(cartPage.isEmpty(), "Cart should have no items on fresh session");
    }

    @Test(description = "Added item should appear in cart")
    public void testItemAppearsInCart() {
        inventoryPage.addToCart(0);
        inventoryPage.openCart();
        Assert.assertEquals(cartPage.getItemCount(), 1);
    }

    @Test(description = "Removing an item from cart should empty it")
    public void testRemoveItemFromCart() {
        inventoryPage.addToCart(0);
        inventoryPage.openCart();
        cartPage.removeItem(0);
        Assert.assertTrue(cartPage.isEmpty());
    }

    @Test(description = "Multiple items can all be removed one by one")
    public void testRemoveAllItems() {
        inventoryPage.addToCart(0);
        inventoryPage.addToCart(1);
        inventoryPage.openCart();
        // remove both, working backwards to avoid index shift issues
        cartPage.removeItem(0);
        cartPage.removeItem(0);
        Assert.assertTrue(cartPage.isEmpty());
    }

    @Test(description = "Continue shopping button should go back to inventory")
    public void testContinueShoppingNavigation() {
        inventoryPage.openCart();
        cartPage.continueShopping();
        Assert.assertTrue(inventoryPage.isLoaded());
    }
}
