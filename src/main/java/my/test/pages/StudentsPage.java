package my.test.pages;

import my.test.blocks.PageHeader;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StudentsPage extends Page {

    public PageHeader pageHeader;

    @FindBy(className = "sg-title-h1")
    private WebElement header;

    @FindBy(linkText = "WileyPLUS")
    private WebElement wileyPlusLink;

    public StudentsPage(WebDriver driver) {
        super(driver);
        pageHeader = new PageHeader(driver);
        PageFactory.initElements(driver, this);
    }

    public void checkStudentsHeader() {
        checkHeader(header, "Students");
    }

    public void checkWileyPlusLink() {
        Assert.assertTrue(wileyPlusLink.isDisplayed());
        Assert.assertEquals("http://wileyplus.wiley.com/", wileyPlusLink.getAttribute("href"));
    }

    public void moveCursorToSubMenuItem(String subMenuItemName) {
        moveCursorToElement(pageHeader.subjectsSubMenuItems, subMenuItemName);
    }

    public void clickDropdownMenuItem(String dropdownMenuItemName) {
        clickItem(pageHeader.eToLDropdownMenuItems, dropdownMenuItemName);
    }

}
