package my.task;

import my.test.pages.EducationPage;
import my.test.pages.HomePage;
import my.test.pages.SearchResultsPage;
import my.test.pages.StudentsPage;
import my.test.pages.UndetectedCountryModalWindow;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Path;
import java.nio.file.Paths;


public class WileyTest {
    @Test
    public void test() {
        //1. Open http://www.wiley.com/WileyCDA/
        driver.get("http://www.wiley.com/WileyCDA/");
        driver.manage().window().maximize();
        //I got a modal window during testing,
        // so I clicked on the button to go to the https://www.wiley.com/en-us link
        UndetectedCountryModalWindow modalWindow = new UndetectedCountryModalWindow(driver);
        modalWindow.clickConfirmationButton();
        modalWindow.waitModalWindowToDisappear();
        HomePage homePage = new HomePage(driver);
        //Check links are displayed in the top menu
        homePage.pageHeader.checkTopMenuItems(topMenuItems);
        //2. Check items under Resources for sub-header
        homePage.pageHeader.moveCursorToTopMenuItem("Resources");
        //There are 10 items under resources sub-header
        homePage.pageHeader.checkNumberOfSubMenuItem(10);
        //Check titles of items
        homePage.pageHeader.checkSubMenuItems(subMenuItems);
        //3. Click “Students” item
        homePage.pageHeader.clickSubMenuItem("Students");
        StudentsPage studentsPage = new StudentsPage(driver);
        studentsPage.waitForPageLoading();
        //Check that https://www.wiley.com/en-ru/students url is opened
        studentsPage.checkPage("https://www.wiley.com/en-us/students");
        //Check that “Students” header is displayed
        studentsPage.checkStudentsHeader();
        //Check “WileyPLUS” link is present on page and it leads to http://wileyplus.wiley.com/ url
        studentsPage.checkWileyPlusLink();
        //4. Go to “Subjects” top menu, select “E-L”, and then “Education”
        studentsPage.pageHeader.moveCursorToTopMenuItem("Subjects");
        studentsPage.moveCursorToSubMenuItem("E-L");
        studentsPage.clickDropdownMenuItem("Education");
        EducationPage educationPage = new EducationPage(driver);
        //Check “Education” header is displayed
        educationPage.checkEducationHeader();
        //13 items are displayed under “Subjects” on the left side of the screen
        educationPage.checkNumberOfSidePanelItems(13);
        //and the texts are
        educationPage.checkSidePanelItems(sidePanelItems);
        //5. Click on the Wiley logo at the top menu
        educationPage.pageHeader.clickLogo();
        HomePage openedHomePage = new HomePage(driver);
        //Home page is opened
        openedHomePage.checkPage("https://www.wiley.com/en-us");
        //6. Do not enter anything in the search input and press search button
        openedHomePage.pageHeader.clickSearchButton();
        //Nothing happens, home page is still displayed
        openedHomePage.checkPage("https://www.wiley.com/en-us");
        //7. Enter “Math” and do not press search button
        openedHomePage.pageHeader.typeTextInSearchString("Math");
        //Area with related content is displayed right under the search header
        openedHomePage.pageHeader.checkRelatedContentArea();
        //On the left side, it has 4 words starting with “Math”
        openedHomePage.pageHeader.checkNumberOfItemsInSearchList(4);
        openedHomePage.pageHeader.checkSearchList();
        //On the right side, there are 4 titles under “Related content” and each title contain “Math” word
        openedHomePage.pageHeader.checkNumberOfItemsInRelatedContent(4);
        openedHomePage.pageHeader.checkRelatedContent();
        //8. Click “SEARCH” button
        openedHomePage.pageHeader.clickSearchButton();
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        //Only titles containing “Math” are displayed
        searchResultsPage.checkTitles();
        //There are 10 titles
        searchResultsPage.checkNumberOfTitles(10);
        //Each title has at least one “Add to Cart” button
        searchResultsPage.checkAddToCartButton();
        searchResultsPage.saveTitlesList();
        //Enter “Math” in the search input at the top and press “SEARCH” button
        searchResultsPage.pageHeader.typeTextInSearchString("Math");
        searchResultsPage.pageHeader.clickSearchButton();
        SearchResultsPage equalitySearchResultsPage = new SearchResultsPage(driver);
        //Make sure there are same 10 titles shown (as in step 8)
        equalitySearchResultsPage.compareTitles();
    }

    private static WebDriver driver;
    private static String[] topMenuItems;
    private static String[] subMenuItems;
    private static String[] sidePanelItems;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        topMenuItems = new String[]{"Resources", "Subjects", "About"};
        subMenuItems = new String[]{"Students", "Instructors", "Researchers", "Professionals", "Librarians",
                "Institutions", "Authors", "Resellers", "Corporations", "Societies"};
        sidePanelItems = new String[]{"Information & Library Science", "Education & Public Policy", "K-12 General",
                "Higher Education General", "Vocational Technology", "Conflict Resolution & Mediation (School settings)",
                "Curriculum Tools- General", "Special Educational Needs", "Theory of Education", "Education Special Topics",
                "Educational Research & Statistics", "Literacy & Reading", "Classroom Management"};
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();
    }
}
