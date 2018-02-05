package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import blocks.PageHeader;
import java.util.List;

public class SearchResultsPage extends Page{

    public PageHeader pageHeader;

    private static List<WebElement> list;

    @FindBy(className = "product-title")
    private List<WebElement> titlesList;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        pageHeader = new PageHeader(driver);
        PageFactory.initElements(driver, this);
    }

    public void checkTitles(){
        for (WebElement title : titlesList){
            Assert.assertTrue(getItemName(title).matches(".*Math.*"));
        }
    }

    public void checkNumberOfTitles(int expectedNumberOfTitles) {
        Assert.assertEquals(null, expectedNumberOfTitles, titlesList.size());
    }

    public void checkAddToCartButton(){
        for (WebElement title : titlesList){
            Assert.assertTrue(title.findElement(
                    By.xpath(".//following-sibling::div/div/div/div/div/div[@class = 'product-button']"))
                    .isDisplayed());
        }
    }

    public void saveTitlesList(){
        list = titlesList;
    }

    public void compareTitles(){
        Assert.assertEquals(list, titlesList);
    }
}
