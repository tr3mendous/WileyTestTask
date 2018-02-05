package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UndetectedCountryModalWindow {

    private WebDriver driver;

    @FindBy(id = "selectCountryModalWnd")
    private WebElement modalWindow;

    @FindBy(xpath = "//button[contains(@class, 'changeLocationConfirmBtn')]")
    private WebElement confirmationButton;

    public UndetectedCountryModalWindow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickConfirmationButton() {
        if (modalWindow.isDisplayed()) {
            confirmationButton.click();
        }
    }

    public void waitModalWindowToDisappear() {
        (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver arg0) {
                        return !modalWindow.isDisplayed();
                    }
                });
    }
}
