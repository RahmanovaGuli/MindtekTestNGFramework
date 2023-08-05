package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BrowserUtils {
    /**
     * This method generate random emails
     * Ex
     *   .getRandomEmail();  -> returns user45-12jvnvnv-hfugu@gmail.com
     */
    public static String getRandomEmail(){
        UUID uuid = UUID.randomUUID();
        return "user"+uuid+"@gmail.com";
    }
    /**
     * This method takes screenshots.
     * Ex:
     * .takeScreenshot("NameofScreenshot");
     */
    public static void takeScreenshot(String testName) throws IOException {
        WebDriver driver = Driver.getDriver();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = "src/test/resources/screenshots/"+testName+".png";
        File file = new File(path);
        FileUtils.copyFile(screenshot,file);
    }

    /**
     * This method accepts a WebElement and a String value to select
     * an option from a dropdown by value .
     * Ex:
     *    .selectDropdownByValue(WebElement dropdown, String value);
     */

    public static void selectDropdownByValue(WebElement dropdown, String value){
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }
    /**
     * This method will create a WebDriverWait object and wait for certain
     * text to be present in a WebElement
     * Ex:
     *     .waitForTextToBePresentInElement(WebElement element, String expectedText);
     */
    public static void waitForTextToBePresentInElement(WebElement element, String expectedText){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),15);
        wait.until(ExpectedConditions.textToBePresentInElement(element,expectedText));
    }

    /**
     * This method waits for certain element to be clickable.
     * Ex:
     *       .waitForElementToBeClickable(WebElement element);
     * @param
     */
    public static void waitForElementToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),15);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
