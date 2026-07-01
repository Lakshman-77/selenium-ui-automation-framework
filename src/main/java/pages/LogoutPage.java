package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogoutPage extends BasePage {

    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By closeMenuButton = By.id("react-burger-cross-btn");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public void openMenu() {
        click(menuButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
    }

    public void logout() {
        openMenu();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }

    public void closeMenu() {
        click(closeMenuButton);
    }
}