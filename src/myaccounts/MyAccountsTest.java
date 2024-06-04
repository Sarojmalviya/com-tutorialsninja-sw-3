package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
/*
/**
 * 1. Create the class MyAccountsTest
 * 1.1 create method with name "selectMyAccountOptions" it has one parameter name
 * "option" of type string
 * 1.2 This method should click on the options whatever name is passed as parameter.
 * (Hint: Handle List of Element and Select options)
 * Write the following test
 * 1. Test name verifyUserShouldNavigateToRegisterPageSuccessfully()
 * 1.1 Click on My Account Link.
 * 1.2 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Register”
 * 1.3 Verify the text “Register Account”.
 */

    //1.1 create method with name "selectMyAccountOptions" it has one parameter name "option" of type string
    public void selectMyAccountOptions (String option){

        //1.2 This method should click on the options whatever name is passed as parameter
        List<WebElement> names = driver.findElements(By.xpath("//ul[@class = 'list-inline']//a"));
        for (WebElement n : names){
            System.out.println(n.getText());
            if (n.getText().equalsIgnoreCase(option)){
                n.click();
                break;
            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        clickOnElement(By.xpath("//span[normalize-space()='My Account']")); //Click on My Account Link.
        selectMyAccountOptions("Register");//Call the method “selectMyAccountOptions” method and pass the parameter Register
        //Verify the text “Register Account”.
        Assert.assertEquals("Register Account", getTextFromElement(By.xpath("//h1[normalize-space()='Register Account']")));
    }

    /*
     * 2. Test name verifyUserShouldNavigateToLoginPageSuccessfully()
     * 2.1 Click on My Account Link.
     * 2.2 Call the method “selectMyAccountOptions” method and pass the parameter
     * “Login”
     * 2.3 Verify the text “Returning Customer”.
     */
    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        //Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        //Call the method “selectMyAccountOptions” method and pass the parameter Login
        selectMyAccountOptions("Login");
        //Verify the text “Returning Customer”.
        Assert.assertEquals("Returning Customer", getTextFromElement(By.xpath("//h2[normalize-space()='Returning Customer']")));
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
        //Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        selectMyAccountOptions("Register");
        sendTextToElement(By.id("input-firstname"), "manish");//Enter First Name
        sendTextToElement(By.id("input-lastname"), "tyagi");  //Enter Last Name
        //Enter Email
        String userName = "" + (int) (Math.random() * Integer.MAX_VALUE);  //Create random username
        String emailID = "User" + userName + "@example.com";
        sendTextToElement(By.id("input-email"), emailID);
        sendTextToElement(By.id("input-telephone"), "078363879543"); //Enter Telephone
        sendTextToElement(By.id("input-password"), "abc123"); //Enter Password
        sendTextToElement(By.id("input-confirm"), "abc123");//Enter Password Confirm
        clickOnElement(By.xpath("//label[normalize-space()='Yes']"));//Select Subscribe Yes radio button
        clickOnElement(By.xpath("//input[@name='agree']"));//Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@value='Continue']"));//Click on Continue button
        //Verify the message “Your Account Has Been Created!”
        Assert.assertEquals("Your Account Has Been Created!", getTextFromElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")));
        clickOnElement(By.xpath("//a[normalize-space()='Continue']")); //Click on Continue button
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));//Click on My Account Link.
        selectMyAccountOptions("Logout");//Verify the text “Account Logout”
        Assert.assertEquals("Account Logout", getTextFromElement(By.xpath("//div[@id='content']//h1")));
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));  //Click on Continue button
    }

    /*
     * 4. Test name verifyThatUserShouldLoginAndLogoutSuccessfully()
     * 4.1 Click on My Account Link.
     * 4.2 Call the method “selectMyAccountOptions” method and pass the parameter
     * “Login”
     * 4.3 Enter Email address
     * 4.4 Enter Last Name
     * 4.5 Enter Password
     * 4.6 Click on Login button
     * 4.7 Verify text “My Account”
     * 4.8 Click on My Account Link.
     * 4.9 Call the method “selectMyAccountOptions” method and pass the parameter
     * “Logout”
     * 4.10 Verify the text “Account Logout”
     * 4.11 Click on Continue button
     */
    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {

        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));//Click on My Account Link.
        selectMyAccountOptions("Login");

        sendTextToElement(By.id("input-email"), "abc123465@gmail.com");//Enter Email address

        sendTextToElement(By.id("input-password"), "abc123");//Enter Password

        clickOnElement(By.xpath("//input[@value='Login']")); //Click on Login button

        verifyExpectedAndActual(By.xpath("//h2[contains(text(),'My Account')]"),"My Account"); //Verify text “My Account”

        mouseHoverToElementAndClick(By.xpath("//span[contains(text(),'My Account')]"));//Click on My Account Link

        selectMyAccountOptions("Logout");

        verifyExpectedAndActual(By.xpath("//h1[contains(text(),'Account Logout')]"),"Account Logout");//Verify the text “Account Logout”

        mouseHoverToElementAndClick(By.xpath("//a[contains(text(),'Continue')]")); //Click on Continue button

    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}

