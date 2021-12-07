package my.test.blocks;

import my.test.pages.Page;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageHeader extends Page {

    @FindBy(xpath = "//div[@class = 'yCmsContentSlot logo']")
    public WebElement wileyLogo;

    @FindBy(xpath = "//ul[@class = 'navigation-menu-items']/li")
    public List<WebElement> topMenuItems;

    @FindBy(xpath = "//div[@id = 'navigationNode_00000RS2']/div[@class = 'dropdown-items-wrapper']/h3")
    public List<WebElement> resourcesSubMenuItems;

    @FindBy(xpath = "//div[@id = 'navigationNode_00000RS5']/div[@class = 'dropdown-items-wrapper']/h3")
    public List<WebElement> subjectsSubMenuItems;

    @FindBy(xpath = "//a[@title = 'E-L']/following-sibling::ul/li[@class = 'yCmsComponent']")
    public List<WebElement> eToLDropdownMenuItems;

    @FindBy(className = "js-site-search-input")
    public WebElement searchString;

    @FindBy(className = "input-group-btn")
    public WebElement searchButton;

    @FindBy(xpath = "//form/div[contains(@class, 'main-navigation-search-autocomplete')]")
    public WebElement relatedContentArea;

    @FindBy(xpath = "//div[@class = 'search-list']/div[@class = 'ui-menu-item']")
    public List<WebElement> searchList;

    @FindBy(xpath = "//div[@class = 'search-related-content']/section/div[@class = 'related-content-products']/section[@class = 'product-item ui-menu-item']")
    public List<WebElement> searchRelatedContent;

    public PageHeader(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void checkTopMenuItems(String[] topMenuItemNames) {
        checkItems(topMenuItems, topMenuItemNames);
    }

    public void checkSubMenuItems(String[] subMenuItemNames) {
        checkItems(resourcesSubMenuItems, subMenuItemNames);
    }

    public void checkNumberOfSubMenuItem(int expectedNumberOfSubMenuItems) {
        Assert.assertEquals(null, expectedNumberOfSubMenuItems, resourcesSubMenuItems.size());
    }

    public void checkNumberOfItemsInSearchList(int expectedNumberOfItemsInSearchList) {
        Assert.assertEquals(null, expectedNumberOfItemsInSearchList, searchList.size());
    }

    public void checkNumberOfItemsInRelatedContent(int expectedNumberOfItemsInRelatedContent) {
        Assert.assertEquals(null, expectedNumberOfItemsInRelatedContent, searchRelatedContent.size());
    }

    public void moveCursorToTopMenuItem(String topMenuItemName) {
        moveCursorToElement(topMenuItems, topMenuItemName);
    }

    public void clickSubMenuItem(String subMenuItemName) {
        clickItem(resourcesSubMenuItems, subMenuItemName);
    }

    public void clickSearchButton() {
        Assert.assertTrue(searchButton.isDisplayed());
        searchButton.click();
    }

    public void typeTextInSearchString(String text) {
        Assert.assertTrue(searchString.isDisplayed());
        searchString.sendKeys(text);
    }

    public void checkRelatedContentArea() {
        waitElementToBeVisible(relatedContentArea);
    }

    public void checkSearchList() {
        for (WebElement element : searchList) {
            Assert.assertTrue(getItemName(element).matches("Math.*"));
        }
    }

    public void checkRelatedContent() {
        for (WebElement element : searchRelatedContent) {
            String[] nameAndAuthor = getItemName(element).split("\n");
            Assert.assertTrue(nameAndAuthor[0].matches(".*Math.*"));
        }
    }

    public void checkItemFromList(List<WebElement> items, String itemName) {
        WebElement element = getItemFromList(items, itemName);
        Assert.assertTrue(element.isDisplayed());
    }

    public void clickLogo() {
        Assert.assertTrue(wileyLogo.isDisplayed());
        wileyLogo.click();
    }

}


