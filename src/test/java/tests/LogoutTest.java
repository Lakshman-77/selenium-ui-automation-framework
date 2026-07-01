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

    @Test(description = "Logout should redirect back to login page")
    public void testLogoutRedirectsToLogin() {
        logoutPage.logout();

        Assert.assertTrue(
                loginPage.isLoginFormVisible(),
                "Login form should be visible after logout."
        );
    }

    @Test(description = "After logout, login form should be visible again")
    public void testLoginFormVisibleAfterLogout() {
        logoutPage.logout();

        Assert.assertTrue(
                loginPage.isLoginFormVisible(),
                "Login form should appear after logout."
        );
    }

    @Test(description = "Session should be cleared — back button should not re-enter app")
    public void testBackButtonAfterLogout() {
        logoutPage.logout();

        driver.navigate().back();

        Assert.assertFalse(
                inventoryPage.isLoaded(),
                "Navigating back after logout should not restore authenticated session."
        );
    }
}