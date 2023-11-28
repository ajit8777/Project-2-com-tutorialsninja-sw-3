package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

/**
 * Create package myaccounts
 * 1. Create the class MyAccountsTest
 * 1.1 create method with name "selectMyAccountOptions" it has one parameter name
 * "option" of type string
 * 1.2 This method should click on the options whatever name is passed as parameter.
 * (Hint: Handle List of Element and Select options)
 * <p>
 * Write the following test
 * 1. Test name verifyUserShouldNavigateToRegisterPageSuccessfully()
 * <p>
 * 1.1 Click on My Account Link.
 * 1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
 * 1.3 Verify the text “Register Account”.
 * <p>
 * 2. Test name verifyUserShouldNavigateToLoginPageSuccessfully()
 * <p>
 * 2.1 Click on My Account Link.
 * 2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
 * 2.3 Verify the text “Returning Customer”.
 * <p>
 * 3. Test name verifyThatUserRegisterAccountSuccessfully()
 * <p>
 * 3.1 Click on My Account Link.
 * 3.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
 * 3.3 Enter First Name
 * 3.4 Enter Last Name
 * 3.5 Enter Email
 * 3.6 Enter Telephone
 * 3.7 Enter Password
 * 3.8 Enter Password Confirm
 * 3.9 Select Subscribe Yes radio button
 * 3.10 Click on Privacy Policy check box
 * 3.11 Click on Continue button
 * 3.12 Verify the message “Your Account Has Been Created!”
 * 3.13 Click on Continue button
 * <p>
 * 3.14 Clickr on My Account Link.
 * 3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
 * 3.16 Verify the text “Account Logout”
 * 3.17 Click on Continue button
 * <p>
 * 4. Test name verifyThatUserShouldLoginAndLogoutSuccessfully()
 * <p>
 * 4.1 Clickr on My Account Link.
 * 4.2 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Login”
 * 4.3 Enter Email address
 * 4.4 Enter Last Name
 * 4.5 Enter Password
 * 4.6 Click on Login button
 * 4.7 Verify text “My Account”
 * 4.8 Clickr on My Account Link.
 * 4.9 Call the method “selectMyAccountOptions” method and pass the parameter
 * “Logout”
 * 4.10 Verify the text “Account Logout”
 * 4.11 Click on Continue button
 */

public class MyAccountsTest extends Utility {
    @Before
    public void setUp() {
        openBrowser("http://tutorialsninja.com/demo/index.php?");
    }

    public void selectMyAccountOptions(String option) {
        //This method should click on the options whatever name is passed as parameter.
        List<WebElement> registerList = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']/li"));//list with two options(do multi select)
        for (WebElement option1 : registerList) {
            if (option1.getText().equals(option)) {
                option1.click();
                break;
            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));//myaccount
        selectMyAccountOptions("Register");//to click on register button
        String expectedTextRegister = "Register Account";
        String actualTextRegister = getTextFromElement(By.xpath("//div[@id='content']/h1"));
        //verify if expected equals actual
        Assert.assertEquals("not on register page", expectedTextRegister, actualTextRegister);
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));//myaccount
        selectMyAccountOptions("Login");//to click on login button
        String expectedTextLogin = "Returning Customer";
        String actualTextLogin = getTextFromElement(By.xpath("//div[@class='well']/h2[text()='Returning Customer']"));
        //verify if expected equals actual
        Assert.assertEquals("not on login page", expectedTextLogin, actualTextLogin);
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));//myaccount
        selectMyAccountOptions("Register");//to click on register button
        //3.3 Enter First Name
        sendTextOnElement(By.name("firstname"), "Iam");
        //3.4 Enter Last Name
        sendTextOnElement(By.name("lastname"), "Patel");
        //3.5 Enter Email
        sendTextOnElement(By.name("email"), "pravin456@gmail.com");
        //3.6 Enter Telephone
        sendTextOnElement(By.name("telephone"), "07654345678");
        //3.7 Enter Password
        sendTextOnElement(By.name("password"), "HelloNum1");
        //3.8 Enter Password Confirm
        sendTextOnElement(By.name("confirm"), "HelloNum1");
        //3.9 Select Subscribe Yes radio button
        clickOnElement(By.name("newsletter"));
        //3.10 Click on Privacy Policy check box
        clickOnElement(By.name("agree"));
        //3.11 Click on Continue button
        clickOnElement(By.xpath("//input[@type='submit']"));
        //expected text
        String expectedCreation = "Your Account Has Been Created!";
        //actual text
        String actualCreation = getTextFromElement(By.xpath("//div[@id='content']/h1"));
        //3.12 Verify the message “Your Account Has Been Created!”
        Assert.assertEquals("account not created", expectedCreation, actualCreation);
        //3.13 Click on Continue button
        clickOnElement(By.xpath("//a[text()='Continue']"));
        //3.14 Click on My Account Link.
        clickOnElement(By.xpath("//a[@title='My Account']"));
        //3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
        String expectedTextLogout = "Account Logout";
        String actualTextLogout = getTextFromElement(By.xpath("//h1[contains(text(),'Account Logout')]"));
        //3.16 Verify the text “Account Logout”
        Assert.assertEquals("not logged out", expectedTextLogout, actualTextLogout);
        //3.17 Click on Continue button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        clickOnElement(By.xpath("//span[contains(text(),'My Account')]"));//myaccount
        selectMyAccountOptions("Login");//to click on register button
        //enter email
        sendTextOnElement(By.name("email"), "iampatel@gmail.com");
        //enter password
        sendTextOnElement(By.name("password"), "HelloNum1");
        //click login
        clickOnElement(By.xpath("//input[@value='Login']"));
        String expectedTextMyAccount = "My Account";
        String actualTextMyAccount = getTextFromElement(By.xpath("//div[@id='content']/h2[text()='My Account']"));
        //verify if expected equals actual
        Assert.assertEquals("Not on my account", expectedTextMyAccount, actualTextMyAccount);
        //click my account
        clickOnElement(By.xpath("//a[@title='My Account']"));
        //choose logout
        selectMyAccountOptions("Logout");
        String expectedTextLogout = "Account Logout";
        String actualTextLogout = getTextFromElement(By.xpath("//div[@id='content']/h1"));
        //verify if expected equals actual
        Assert.assertEquals("Not logged out", expectedTextLogout, actualTextLogout);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
