package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import pages.LogoutPage;

public class LogoutTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private LogoutPage logoutPage;

    @BeforeMethod
    public void init() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        logoutPage = new LogoutPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Browser back should not restore authenticated session after logout")
    public void testBackButtonAfterLogout() {

        logoutPage.logout();

        driver.navigate().back();

        Assert.assertFalse(
                inventoryPage.isLoaded(),
                "Navigating back after logout should not restore authenticated session."
        );
    }
}