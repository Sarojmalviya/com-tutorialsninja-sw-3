package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {                   //Open browser
        this.openBrowser(this.baseUrl);
    }

    public void selectMenu(String menu) {
        List<WebElement> menuElement = getMultipleElements(By.xpath("//a[@class='see-all']"));
        for (WebElement m : menuElement) {
            if (m.getText().equalsIgnoreCase(menu)) {
                m.click();
                break;
            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() throws InterruptedException {

        mouseHoverToElementAndClick(By.xpath("//a[normalize-space()='Desktops']")); //Mouse hover on “Desktops” Tab and click
        selectMenu("Show AllDesktops"); //call selectMenu method and pass the menu = “Show All Desktops”
        Assert.assertEquals("Desktops",getTextFromElement(By.xpath("//h2[text()='Desktops']"))); //Verify the text ‘Desktops’

    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        //Mouse hover on “Laptops & Notebooks” Tab and click
        mouseHoverToElementAndClick(By.xpath("//a[normalize-space()='Laptops & Notebooks']"));
        //call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");
        //Verify the text ‘Laptops & Notebooks’
        Assert.assertEquals("Laptops & Notebooks", getTextFromElement(By.xpath("//h2[text()='Laptops & Notebooks']")));
    }
    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {

        mouseHoverToElementAndClick(By.xpath("//a[normalize-space()='Components']")); //Mouse hover on “Components” Tab and click
        selectMenu("Show AllComponents");//call selectMenu method and pass the menu = “Show All Components”
        Assert.assertEquals("Components", getTextFromElement(By.xpath("//h2[text()='Components']"))); //Verify the text ‘Components’
    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}