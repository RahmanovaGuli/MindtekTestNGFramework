package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.WeatherBaseMain;
import utilities.ConfigReader;
import utilities.TestBase;

public class WeatherbaseTestHW extends TestBase {
    /*
    Test case 1:
1. Go to http://www.weatherbase.com/
2. Search for zip code 60656
3. Validate that result is including Chicago, IL

Test case 2:
1. Go to http://www.weatherbase.com/
2. Search for invalid zip code 1234
3. Validate error message 'We're sorry. Your search for 1234 returned no results. Please try the following:  '

     */
    @Test
    public void searchZipCode(){
        driver.get(ConfigReader.getProperty("Weatherbase"));
        driver.findElement(By.xpath("//input[@id=\"query\"]")).sendKeys("60656");
        driver.findElement(By.xpath("//*[@id=\"header-block\"]/table/tbody/tr[2]/td[4]/form/button")).submit();
        String actualMsg = driver.findElement(By.xpath("//*[@id=\"left-content\"]/p[2]")).getText();
        String expectedMSg = "Here are the closest cities to Chicago, Illinois (60656) which have weather records and averages:";
        Assert.assertEquals(actualMsg,expectedMSg);
    }
    @Test
    public void invalidZipCode(){
        driver.get(ConfigReader.getProperty("Weatherbase"));
        driver.findElement(By.xpath("//input[@id=\"query\"]")).sendKeys("1234");
        driver.findElement(By.xpath("//*[@id=\"header-block\"]/table/tbody/tr[2]/td[4]/form/button")).submit();
        String actualMsg = driver.findElement(By.xpath("//*[@id=\"left-content\"]/p[1]")).getText();
        String expectedMSg = "We're sorry. Your search for 1234 returned no results. Please try the following:";
        Assert.assertEquals(actualMsg,expectedMSg);
    }
    /*
    1. Go to http://www.weatherbase.com/
2. Click on North America then United States of America
3. Validate that page has listed at least 50 states

     */
    @Test
    public void states50(){
        driver.get(ConfigReader.getProperty("Weatherbase"));
        WeatherBaseMain weatherBaseMain = new WeatherBaseMain();
        weatherBaseMain.clickNAmerica.click();
        weatherBaseMain.clickUSA.click();
        String actualMsg = driver.findElement(By.xpath("//div[@id=\"headerfont\"]")).getText();
        String expectedMSg = "UNITED STATES OF AMERICA";
        Assert.assertEquals(actualMsg,expectedMSg);
    }
    /*

Test case 4:
1. Go to http://www.weatherbase.com/
2. Click on North America, United States of America, California, and San Jose
3. Then click on Celsius
4. Validate that in Average Temperature table it will display 'C' letter

     */
    @Test
    public void californiaAndSanJose(){
        driver.get(ConfigReader.getProperty("Weatherbase"));
        WeatherBaseMain weatherBaseMain = new WeatherBaseMain();
        weatherBaseMain.clickNAmerica.click();
        weatherBaseMain.clickUSA.click();
        weatherBaseMain.clickCalifornia.click();
        weatherBaseMain.clickSanJose.click();
//        Select select = new Select(weatherBaseMain.clickSanJose);
//        select.selectByValue("San Jose");
        weatherBaseMain.clickCelsius.click();
        String actualMsg = weatherBaseMain.clickCelsius.getText();
        String expectedMSg = "°C";
        Assert.assertEquals(actualMsg,expectedMSg);
    }
    /*
    Test case 5:
1. Go to http://www.weatherbase.com/
2. Click on North America, United States of America, California, and San Jose
3. Then click on Celsius
4. Validate that temperature  in Fahrenheit is correctly converted to Celsius. (Validate only Annual temperature in Average Temperature table.)

     */
    @Test
    public void fahrenheitConvertedCelsius(){
        driver.get(ConfigReader.getProperty("Weatherbase"));
        WeatherBaseMain weatherBaseMain = new WeatherBaseMain();
        weatherBaseMain.clickNAmerica.click();
        weatherBaseMain.clickUSA.click();
        weatherBaseMain.clickCalifornia.click();
        Select select = new Select(weatherBaseMain.clickSanJose);
        select.selectByValue("San Jose");
        weatherBaseMain.clickCelsius.click();

    }
}
