package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import blocks.PageHeader;
import java.util.List;

public class EducationPage extends Page {

    public PageHeader pageHeader;

    @FindBy(xpath = "//div[@class = 'wiley-slogan']/h1[@style]/span[@style]")
    private WebElement header;

    @FindBy(xpath = "//div[@class = 'side-panel']/ul/li")
    private List<WebElement> sidePanelItems;

    public EducationPage(WebDriver driver) {
        super(driver);
        pageHeader = new PageHeader(driver);
        PageFactory.initElements(driver, this);
    }

    public void checkEducationHeader() {
        checkHeader(header, "Education");
    }

    public void checkNumberOfSidePanelItems(int expectedNumberOfSidePanelItemsItems) {
        Assert.assertEquals(null, expectedNumberOfSidePanelItemsItems, sidePanelItems.size());
    }

    public void checkSidePanelItems(String[] sidePanelItemNames) {
        checkItems(sidePanelItems, sidePanelItemNames);
    }
}
