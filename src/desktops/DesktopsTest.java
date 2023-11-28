package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Create class “DesktopsTest”
 * Write the following Test:
 * 1.Test name verifyProductArrangeInAlphaBaticalOrder()
 * 1.1 Mouse hover on Desktops Tab.and click
 * 1.2 Click on “Show All Desktops”
 * 1.3 Select Sort By position "Name: Z to A"
 * 1.4 Verify the Product will arrange in Descending order.
 * 2. Test name verifyProductAddedToShoppingCartSuccessFully()
 * <p>
 * 2.1 Mouse hover on Currency Dropdown and click
 * 2.2 Mouse hover on £Pound Sterling and click
 * 2.3 Mouse hover on Desktops Tab.
 * 2.4 Click on “Show All Desktops”
 * 2.5 Select Sort By position "Name: A to Z"
 * 2.6 Select product “HP LP3065”
 * 2.7 Verify the Text "HP LP3065"
 * 2.8 Select Delivery Date "2023-11-27"
 * 2.9.Enter Qty "1” using Select class.
 * 2.10 Click on “Add to Cart” button
 * 2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
 * 2.12 Click on link “shopping cart” display into success message
 * 2.13 Verify the text "Shopping Cart"
 * 2.14 Verify the Product name "HP LP3065"
 * 2.15 Verify the Delivery Date "2023-11-27"
 * 2.16 Verify the Model "Product21"
 * 2.17 Verify the Todat "£74.73"
 */

public class DesktopsTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() throws InterruptedException {
        // 1.1 Mouse hover on Desktops Tab.and click
        mouseHoverToElementAndClick(By.xpath("//a[normalize-space()='Desktops']"));

        // 1.2 Click on “Show All Desktops”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));

        // before sorting value
        List<WebElement> beforeSortValue = driver.findElements(By.xpath("//div[@class='caption']//h4"));
        List<String> beforeSortDesktopValue = new ArrayList<>();
        for (WebElement value : beforeSortValue) {
            beforeSortDesktopValue.add(value.getText());

            //1.3 Select Sort By position "Name: Z to A"
            selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (Z - A)");
            Thread.sleep(1000);
            // After shorting value
            List<WebElement> afterSortValue = driver.findElements(By.xpath("//div[@class='caption']//h4"));
            List<String> afterSortDesktopValue = new ArrayList<>();
            Thread.sleep(1000);
            for (WebElement value1 : afterSortValue) {
                afterSortDesktopValue.add(value1.getText());
            }
            Collections.sort(beforeSortDesktopValue, String.CASE_INSENSITIVE_ORDER);// Ascending order
            Collections.reverse(beforeSortDesktopValue); // descending order
            // 1.4 Verify the Product will arrange in Descending order.
            Assert.assertEquals(beforeSortDesktopValue, afterSortDesktopValue);


        }
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        // 2.1 Mouse hover on Currency Dropdown and click
        mouseHoverToElementAndClick(By.cssSelector(".btn.btn-link.dropdown-toggle"));

        //2.2 Mouse hover on £Pound Sterling and click
        mouseHoverToElementAndClick(By.cssSelector("button[name='GBP']"));

        //2.3 Mouse hover on Desktops Tab.
        mouseHoverToElement(By.xpath("//a[normalize-space()='Desktops']"));

        //2.4 Click on “Show All Desktops”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));

        //2.5 Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='input-sort']"), "Name (A - Z)");

        //2.6 Select product “HP LP3065”
        clickOnElement(By.xpath("//a[normalize-space()='HP LP3065']"));

        //2.7 Verify the Text "HP LP3065"
        String expectedText = "HP LP3065";
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']"));
        Assert.assertEquals(expectedText, actualText);

        //2.8 Select Delivery Date "2023-11-27"
        String year = "2023";
        String month = "November";
        String date = "27";
        clickOnElement(By.xpath("//i[@class='fa fa-calendar']")); // open the calendar
        while (true) {
            String monthYear = getTextFromElement(By.cssSelector("div[class='datepicker-days'] th[class='picker-switch']"));
            System.out.println(monthYear);
            String[] a = monthYear.split(" ");
            String mon = a[0];
            String yer = a[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
            }
        }

        // Select the date
        List<WebElement> allDates = driver.findElements(By.cssSelector(".datepicker-days"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }

        //2.9.Enter Qty "1” using Select class.

        //2.10 Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));

        //2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
        String expectedMessage = "Success: You have added HP LP3065 to your shopping cart!";
        String actualMessage = getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).substring(0, 56);
        //String[] actualmsg = actualMessage.split("×");
        Assert.assertEquals(expectedMessage, actualMessage);

        //2.12 Click on link “shopping cart” display into success message
        Thread.sleep(1000);
        clickOnElement(By.linkText("shopping cart"));

        //2.13 Verify the text "Shopping Cart"
        String expectedMessage1 = "Shopping Cart  (1.00kg)";
        String actualMessage1 = getTextFromElement(By.xpath("//body/div/div/div/h1[1]"));
        Assert.assertEquals(expectedMessage1, actualMessage1);

        //2.14 Verify the Product name "HP LP3065"
        String expectedMessage2 = "HP LP3065";
        String actualMessage2 = getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));
        Assert.assertEquals(expectedMessage2, actualMessage2);

        //2.15 Verify the Delivery Date "2023-11-27"

        //2.16 Verify the Model "Product21"
        String expectedMessage3 = "Product 21";
        String actualMessage3 = getTextFromElement(By.xpath("//td[normalize-space()='Product 21']"));
        Assert.assertEquals(expectedMessage3, actualMessage3);

        //2.17 Verify the Total "£74.73"
        String expectedText4 = "£74.73";
        String actualText4 = getTextFromElement(By.xpath("(//td[contains(text(),'£74.73')])[4]"));
        Assert.assertEquals(expectedText4, actualText4);
    }

    @After
    public void closingBrowser() {
        closeBrowser();
    }
}


