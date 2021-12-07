package my.test.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Page {
    private WebDriver driver;
    private Actions actions;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void checkPage(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    public void moveCursorToElement(List<WebElement> elements, String elementName) {
        WebElement element = getItemFromList(elements, elementName);
        actions.moveToElement(element).build().perform();
    }

    public void checkItems(List<WebElement> items, String[] itemNames) {
        for (String itemName : itemNames) {
            checkItemFromList(items, itemName);
        }
    }

    public void checkItemFromList(List<WebElement> items, String itemName) {
        WebElement element = getItemFromList(items, itemName);
        Assert.assertTrue(element.isDisplayed());
    }

    public WebElement getItemFromList(List<WebElement> items, String itemName) throws NoSuchElementException {
        for (WebElement item : items) {
            Assert.assertTrue(item.isDisplayed());
            if (getItemName(item).equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        throw new NoSuchElementException(itemName + " element not found");
    }

    public String getItemName(WebElement element) {
        return ((JavascriptExecutor) driver).executeScript("return arguments[0].innerText;", element).toString().trim();
    }

    public void clickItem(List<WebElement> items, String subMenuItemName) {
        WebElement element = getItemFromList(items, subMenuItemName);
        element.click();
    }

    public void checkHeader(WebElement header, String headerName){
        Assert.assertTrue(header.isDisplayed());
        Assert.assertEquals(headerName, getItemName(header));
    }

    public void waitForPageLoading() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    public void waitElementToBeVisible(WebElement element) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(element));
    }
}
