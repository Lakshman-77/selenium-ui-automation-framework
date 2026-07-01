package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CheckoutTest extends BaseTest {
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void init() {
        LoginPage loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addToCart(0);
        inventoryPage.openCart();
        cartPage.goToCheckout();
    }

    @Test(description = "Full checkout flow with valid details should confirm order")
    public void testSuccessfulCheckout() {
        checkoutPage.fillShippingInfo("Arjun", "Sharma", "560001");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        Assert.assertTrue(checkoutPage.isOrderConfirmed());
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), "Thank you for your order!");
    }

    @Test(description = "Missing first name should show validation error")
    public void testMissingFirstName() {
        checkoutPage.fillShippingInfo("", "Sharma", "560001");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
    }

    @Test(description = "Missing last name should trigger error")
    public void testMissingLastName() {
        checkoutPage.fillShippingInfo("Arjun", "", "560001");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Last Name is required"));
    }

    @Test(description = "Missing postal code should block order submission")
    public void testMissingPostalCode() {
        checkoutPage.fillShippingInfo("Arjun", "Sharma", "");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Postal Code is required"));
    }

    @Test(description = "Order summary total should be present before finishing")
    public void testOrderTotalIsShown() {
        checkoutPage.fillShippingInfo("Arjun", "Sharma", "560001");
        checkoutPage.clickContinue();
        String total = checkoutPage.getOrderTotal();
        Assert.assertNotNull(total);
        Assert.assertTrue(total.contains("Total:"), "Summary page should display total amount");
    }
}
