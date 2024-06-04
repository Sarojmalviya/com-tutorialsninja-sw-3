package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder()throws InterruptedException{


        mouseHoverToElementAndClick(By.xpath("//a[normalize-space()='Desktops']"));//1.1 Mouse hover on Desktops Tab.and click

        clickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));//1.2 Click on “Show All Desktops”

        List<WebElement> beforeFilterProductNames = getMultipleElements(By.xpath("//div[@class='caption']//h4"));  //Store multiple elements

        List<String> beforeFilterProductNamesList = new ArrayList<>();//Create arraylist
        //Store elements text to array list
        for (WebElement p : beforeFilterProductNames) {
            beforeFilterProductNamesList.add(p.getText().toUpperCase());
        }

        Collections.sort(beforeFilterProductNamesList);//Sort arraylist to ascending order

        Collections.reverse(beforeFilterProductNamesList); //Reverse the list
        //Select Sort By position "Name: Z to A"
        selectByValueFromDropDown(By.id("input-sort"), "https://tutorialsninja.com/demo/index.php?route=product/category&path=20&sort=pd.name&order=DESC");
        Thread.sleep(2000);
        //Store elements after filtering
        List<WebElement> afterFilterProductNames = getMultipleElements(By.xpath("//div[@class='caption']//h4"));
        //Create anothor list to store text of elements after clicking on filter Z to A
        List<String> afterFilterProductNamesList = new ArrayList<>();
        for (WebElement s : afterFilterProductNames) {
            afterFilterProductNamesList.add(s.getText().toUpperCase());
        }
        //Verify the Product will arrange in Descending order.
        //Compare both list
        Assert.assertEquals("Products are not sorted in descending order", afterFilterProductNamesList, beforeFilterProductNamesList);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        //Mouse hover on Currency Dropdown and click
        mouseHoverToElementAndClick(By.xpath("//span[normalize-space()='Currency']"));
        //Mouse hover on £Pound Sterling and click
        mouseHoverToElementAndClick(By.xpath("//button[normalize-space()='£Pound Sterling']"));
        //Mouse hover on Desktops Tab.
        mouseHoverToElementAndClick(By.xpath("//a[normalize-space()='Desktops']"));
        //Click on “Show All Desktops”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));
        //Select Sort By position "Name: A to Z"
        selectByValueFromDropDown(By.id("input-sort"), "https://tutorialsninja.com/demo/index.php?route=product/category&path=20&sort=pd.name&order=ASC");
        //Select product “HP LP3065”
        clickOnElement(By.xpath("//a[normalize-space()='HP LP3065']"));
        //Verify the Text "HP LP3065"
        Assert.assertEquals("HP LP3065", getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']")));
//2.8 Select Delivery Date "2023-11-27"
        String year = "2023";
        String month = "November";
        String date = "27";
        clickOnElement(By.xpath("//div[@class='input-group date']//button[@type='button']")); // Open the calendar
        while (true) {
            String monthAndYear = getTextFromElement(By.xpath("//div[@class='datepicker-days']//thead//tr[1]"));
            String[] a = monthAndYear.split(" ");
            String mon = a[1];
            String yer = a[2];
            if (mon.equals(month) && yer.equals(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
            }
        }
        // Select the Date
        List<WebElement> allDates = driver.findElements(By.xpath("//td[contains(@class,'day')]"));
        for (WebElement dt : allDates) {
            if (dt.getText().equals(date)) {
                dt.click();
                break;
            }
        }
        // selectDayMonthAndYear(By.xpath("//div[@class='input-group date']//button[@type='button']")," 2014","November","27",By.xpath("//div[@class='datepicker-days']//thead//tr[1]"),By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"),By.xpath("//td[contains(@class,'day')]"));
        //Click on “Add to Cart” button
        clickOnElement(By.id("button-cart"));
        //Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        Assert.assertEquals("Success: You have added HP LP3065 to your shopping cart!", getTextFromElement(By.xpath("//div[contains(text(),'Success')]")).substring(0, 56));
        //Click on link “shopping cart” display into success message
        clickOnElement(By.linkText("shopping cart"));
        //Verify the text "Shopping Cart"
        Assert.assertEquals("Shopping Cart",getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).substring(0,13));
        //Verify the Product name "HP LP3065"
        Assert.assertEquals("HP LP3065",getTextFromElement(By.linkText("HP LP3065")));
        //Verify the Delivery Date "2023-11-27"
        Assert.assertEquals("2023-11-27",getTextFromElement(By.xpath("//small[normalize-space()='Delivery Date:2023-11-27']")).substring(14,24));
        //Verify the Model "Product21"
        Assert.assertEquals("Product 21",getTextFromElement(By.xpath("//td[normalize-space()='Product 21']")));
        //Verify the Todat "£74.73"
        Assert.assertEquals("£74.73",getTextFromElement(By.xpath("(//td[contains(text(),'£74.73')])[4]")));
    }
    @After
    public void tearDown() {
        closeBrowser();
    }

}