package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;

public class BlazeDemoHomework extends TestBase {
    /*

Assigned
Estigfend
Test Case 1:
1. Given the user navigates to https://blazedemo.com/index.php
2. And user selects a flight from "San Diego" to "New York"
3. When the user chooses any flight
4. And user provides the following data in the form
| Name | David Clark |
| Address | 123 Washington ave. |
| City | Austin |
| State | TX |
| Zip Code | 12345 |
| Card Type | American Express |
| Credit Card Number | mycreditcardnumber |
| Month | 11 |
| Year | 2025 |
| Name on Card | David Clark  |
5. Then validate the success message "Invalid credit card number! "

Test Case 2:
1. Given the user navigates to https://blazedemo.com/index.php
2. And user selects a flight from "San Diego" to "New York"
3. When the user chooses any flight
4. And user provides the following data in the form
| Name | John Doe |
| Address | 123 Washington ave. |
| City | New York |
| State | NY |
| Zip Code | 12345 |
| Card Type | Visa |
| Credit Card Number | 1234567890 |
| Month | 11 |
| Year | 2025 |
| Name on Card | John Doe |
5. Then validate the success message "Thank you for your purchase today! "
6. Then validate that the last 4 digits of the card match the provided card
7. Then validate the date when it was booked is today's date
     */

    String fromCity = "San Diego";
    String toCity = "New York";

    @Test(groups = {"regression","blazedemo"})
    public void verifyFindFlights(){
        driver.get(ConfigReader.getProperty("BlazedemoURL"));



        WebElement departureDropdown = driver.findElement(By.name("fromPort"));
        Select departureSelect = new Select(departureDropdown);
        departureSelect.selectByValue(fromCity);

        WebElement destinationDropDown = driver.findElement(By.name("toPort"));
        Select destinationsSelect = new Select(destinationDropDown);
        destinationsSelect.selectByValue(toCity);

        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String actualFlightsText = driver.findElement(By.xpath("//h3[contains(text(), 'Flights from')]")).getText();
        String expectedFlightsText = "Flights from " +fromCity+" to " +toCity+":";
        Assert.assertEquals(actualFlightsText,expectedFlightsText);

    }

}
