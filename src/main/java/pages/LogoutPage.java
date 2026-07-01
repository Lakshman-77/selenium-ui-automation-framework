package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage {
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By closeMenuButton = By.id("react-burger-cross-btn");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public void openMenu() {
        click(menuButton);
    }

    public void logout() {
        click(menuButton);
        click(logoutLink);
    }

    public void closeMenu() {
        click(closeMenuButton);
    }
}
