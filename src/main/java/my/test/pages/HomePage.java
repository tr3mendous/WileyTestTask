package my.test.pages;

import my.test.blocks.PageHeader;
import org.openqa.selenium.WebDriver;

/**
 * @author AStekolshchikov
 */
public class HomePage extends Page {
    public PageHeader pageHeader;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
