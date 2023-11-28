package utilities;

import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {

    public void clickOnElement(By by) {
        driver.findElement(by).click();

    }

    public void sendTextOnElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public void mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }
    public void mouseHoverToElementAndClick(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().build().perform();
    }

    public void selectValueFromDropDown(By by, String value) {
        WebElement dropDown = driver.findElement(by);
        // create the object of select class
        Select select = new Select(dropDown);
        select.selectByValue(value);
    }

    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        // create the object of select class
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }
    public void selectDate(By by, By by1, By by2, String year, String month, String date) {

        while (true){
            String monthYear = getTextFromElement(by);
            System.out.println(monthYear);
            String[] a = monthYear.split(" ");
            String mon = a[0];
            String yer = a[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)){
                break;
            }else {
                clickOnElement(by1);
            }
        }

        // Select the date
        List<WebElement> allDates = driver.findElements(by2);
        for (WebElement dt : allDates ) {
            if (dt.getText().equalsIgnoreCase(date)){
                dt.click();
                break;
            }
        }



    }
}
