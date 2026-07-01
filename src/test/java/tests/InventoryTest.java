package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;

public class InventoryTest extends BaseTest {
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void init() {
        LoginPage loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Inventory page should display exactly 6 products")
    public void testProductCount() {
        Assert.assertEquals(inventoryPage.getProductCount(), 6);
    }

    @Test(description = "Page title should say Products")
    public void testPageTitle() {
        Assert.assertEquals(inventoryPage.getTitle(), "Products");
    }

    @Test(description = "Adding one item should update cart badge to 1")
    public void testAddSingleItemToCart() {
        inventoryPage.addToCart(0);
        Assert.assertEquals(inventoryPage.getCartCount(), 1);
    }

    @Test(description = "Cart badge should reflect correct count when adding multiple items")
    public void testAddMultipleItems() {
        inventoryPage.addToCart(0);
        inventoryPage.addToCart(1);
        inventoryPage.addToCart(2);
        Assert.assertEquals(inventoryPage.getCartCount(), 3);
    }

    @Test(description = "Price sort low-to-high should return ascending order")
    public void testSortByPriceLowToHigh() {
        inventoryPage.sortBy("Price (low to high)");
        List<WebElement> prices = inventoryPage.getPriceElements();
        double prev = 0;
        for (WebElement el : prices) {
            double price = Double.parseDouble(el.getText().replace("$", ""));
            Assert.assertTrue(price >= prev, "Prices should be in ascending order");
            prev = price;
        }
    }

    @Test(description = "Price sort high-to-low should return descending order")
    public void testSortByPriceHighToLow() {
        inventoryPage.sortBy("Price (high to low)");
        List<WebElement> prices = inventoryPage.getPriceElements();
        double prev = Double.MAX_VALUE;
        for (WebElement el : prices) {
            double price = Double.parseDouble(el.getText().replace("$", ""));
            Assert.assertTrue(price <= prev, "Prices should be in descending order");
            prev = price;
        }
    }

    @Test(description = "Name sort A to Z should return alphabetically ascending product names")
    public void testSortByNameAToZ() {
        inventoryPage.sortBy("Name (A to Z)");
        List<WebElement> names = inventoryPage.getNameElements();
        for (int i = 0; i < names.size() - 1; i++) {
            String current = names.get(i).getText();
            String next = names.get(i + 1).getText();
            Assert.assertTrue(current.compareToIgnoreCase(next) <= 0,
                    "Names should be in A-Z order but got: " + current + " before " + next);
        }
    }

    @Test(description = "Name sort Z to A should return alphabetically descending product names")
    public void testSortByNameZToA() {
        inventoryPage.sortBy("Name (Z to A)");
        List<WebElement> names = inventoryPage.getNameElements();
        for (int i = 0; i < names.size() - 1; i++) {
            String current = names.get(i).getText();
            String next = names.get(i + 1).getText();
            Assert.assertTrue(current.compareToIgnoreCase(next) >= 0,
                    "Names should be in Z-A order but got: " + current + " before " + next);
        }
    }
}
