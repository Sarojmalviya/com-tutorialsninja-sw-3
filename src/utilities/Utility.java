package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {

    /**
     * This method will click on element
     */

    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    /**
     * This method will get text from element
     */
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    //Store multiple elements
    public List<WebElement> getMultipleElements(By by) {
        return driver.findElements(by);
    }

    /**
     * This method will send text to Element
     */

    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    //*****************             *****************

    public void clearText(By by) {
        Actions actions = new Actions(driver);
        WebElement quantity = driver.findElement(by);
        quantity.clear();
    }


//********************* Verify expected Vs Actual Method***************

    public void verifyExpectedAndActual(By by, String expectedText) {
        String actualText = getTextFromElement(by);
        Assert.assertEquals(expectedText, actualText);
    }

    public void verifyMessage(String expectedMessage, String actualMessage) {
        Assert.assertEquals(expectedMessage, actualMessage);
    }


    //************************* Alert Methods *****************************************************//
    //Total 5 Method Need to cr**********

    /**
     * This method will switch to alert
     */
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    public void switchToAcceptAlert() {
        driver.switchTo().alert().accept();

    } // this method will dismiss alert

    public void switchToDismissAlert() {
        driver.switchTo().alert().dismiss();

    }

    // this method will  get text from alert
    public String getTextFromAlert(By by) {
        return driver.switchTo().alert().getText();
        //return driver.findElement(by).getText();
    }

    // this method will sendTextToAlert(String Text)
    public void sendTextToAlert(By by, String text) {
        driver.switchTo().alert().sendKeys(text);
        //driver.findElement(by).sendTextToAlert(by,text);
    }

    //*************************** Select Class Methods ***************************************//

    //1.selectByValueFromDropDown(By by , String value)
    public void selectByValueFromDropDown(By by, String value) {
        WebElement dropDown = driver.findElement(by);
        // Create the object of Select class
        Select select = new Select(dropDown);
        // Select by visible Text
        select.selectByValue(value);
    }

    //2.selectByIndexFromDropDown(By by , int index)
    public void selectByIndexFromDropDown(By by, int index) {
        WebElement index1 = driver.findElement(by);
        Select select = new Select(index1);
        select.selectByIndex(index);
    }

    //3.selectByVisibleTextFromDropDown(By by , String value)
    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement visibleText = driver.findElement(by);
        Select select = new Select(visibleText);
        select.selectByVisibleText(text);
    }

    // This method will select the option by value
    public void selectOptionByValue(By by, String value) {
        WebElement optionByValue = driver.findElement(by);
        Select select = new Select(optionByValue);
        select.selectByValue(value);

    }

    //  This method will select the option by index
    public void selectOptionByIndex(By by, int index) {
        WebElement optionByIndex = driver.findElement(by);
        Select select = new Select(optionByIndex);
        select.selectByIndex(index);

    }

    // This method will select the option by contains text
    public void selectOptionByContainsText(By by, String text) {
        List<WebElement> allOptions = new Select(driver.findElement(by)).getOptions();
    }

    //***************************** Action Class *************************************
// action Method
    // mouseHoverToElement(By by),
    public void mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);
        WebElement computer = driver.findElement(by);
        actions.moveToElement(computer).build().perform();
    }

    //This method will do mouse hover on element and click
    public void mouseHoverToElementAndClick(By by) {
        Actions actions = new Actions(driver);
        WebElement software = driver.findElement(by);
        actions.moveToElement(software).click().build().perform();

    }

    //*****************************Select Day date and month***********************************************//
    public void selectDayMonthAndYear(By by, String year, String month, String date, By byMonthYear, By byNextYear, By byDate) {
        clickOnElement(by); // Open the calendar
        while (true) {
            String monthAndYear = getTextFromElement(byMonthYear);
            System.out.println(monthAndYear);
            String[] a = monthAndYear.split(" ");
            String mon = a[1];
            String yer = a[2];
            if (mon.equals(month) && yer.equals(year)) {
                break;
            } else {
                clickOnElement(byNextYear);
            }
        }
        // Select the Date
        List<WebElement> allDates = driver.findElements(byDate);
        for (WebElement dt : allDates) {
            if (dt.getText().equals(date)) {
                dt.click();
                break;
            }
        }
    }
}
