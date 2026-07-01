package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn = By.id("finish");
    private final By confirmHeader = By.cssSelector(".complete-header");
    private final By errorMsg = By.cssSelector("[data-test='error']");
    private final By orderTotal = By.cssSelector(".summary_total_label");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillShippingInfo(String firstName, String lastName, String zip) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(postalCodeField, zip);
    }

    public void clickContinue() {
        click(continueBtn);
    }

    public void clickFinish() {
        click(finishBtn);
    }

    public boolean isOrderConfirmed() {
        return isVisible(confirmHeader);
    }

    public String getConfirmationMessage() {
        return getText(confirmHeader);
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }

    public String getOrderTotal() {
        return getText(orderTotal);
    }
}
