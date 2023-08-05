package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.BlazedemoFlightsPage;
import pages.BlazedemoMainPage;
import pages.BlazedemoReservePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.concurrent.TimeUnit;

public class BlazedemoTests extends TestBase {

    String fromCity = "Boston";
    String toCity = "London";

    @Test(groups = {"regression","blazedemo"})
    public void verifyFindFlights(){
//   driver.get("https://blazedemo.com/");
     driver.get(ConfigReader.getProperty("BlazedemoURL"));



      WebElement  departureDropdown = driver.findElement(By.name("fromPort"));
//        Select destinationsSelect = new Select(departureDropdown);
//        departureSelect.selectByValue(fromCity);
        BrowserUtils.selectDropdownByValue(departureDropdown,fromCity);

        WebElement destinationDropDown = driver.findElement(By.name("toPort"));
        BrowserUtils.selectDropdownByValue(destinationDropDown,toCity);
//        Select destinationsSelect = new Select(destinationDropDown);
//        destinationsSelect.selectByValue(toCity);

        driver.findElement(By.xpath("//input[@type='submit']")).click();

       String actualFlightsText = driver.findElement(By.xpath("//h3[contains(text(), 'Flights from')]")).getText();
        String expectedFlightsText = "Flights from " +fromCity+" to " +toCity+":";
        Assert.assertEquals(actualFlightsText,expectedFlightsText);

    }


    @Test(groups = {"regression","smoke","blazedemo"})
    public void verifyDestinationOfTheWeek(){
        driver.get(ConfigReader.getProperty("BlazedemoURL"));
        driver.findElement(By.xpath("//a[@href='vacation.html']")).click();
        String actualDestinationText = driver.findElement(By.xpath("//div[contains(text(),'Destination of the week')]")).getText();
        Assert.assertEquals(actualDestinationText,"Destination of the week: Hawaii !");
    }
    @Test(groups = {"regression","blazedemo"})
    public void verifyFlightInfo(){

        driver.get(ConfigReader.getProperty("BlazedemoURL"));
        BlazedemoMainPage blazedemoMainPage= new BlazedemoMainPage();
//        Select departureSelect= new Select(blazedemoMainPage.departureDropdown);
//        Select destinationSelect = new Select(blazedemoMainPage.destinationDropdown);
//        departureSelect.selectByValue(fromCity);
//        destinationSelect.selectByValue(toCity);
        BrowserUtils.selectDropdownByValue(blazedemoMainPage.departureDropdown,fromCity);
        BrowserUtils.selectDropdownByValue(blazedemoMainPage.destinationDropdown,toCity);
        blazedemoMainPage.findFlightsBtn.click();

        BlazedemoFlightsPage blazedemoFlightsPage = new BlazedemoFlightsPage();
        String expectedFlightNumber = "Flight Number: "+blazedemoFlightsPage.flightNumber.getText();
        String expectedAirlineName = "Airline: "+blazedemoFlightsPage.airlineName.getText();
        String expectedPrice = blazedemoFlightsPage.price.getText().substring(1);
        blazedemoFlightsPage.chooseFlightBtn.click();

        SoftAssert softAssert = new SoftAssert();

        BlazedemoReservePage blazedemoReservePage = new BlazedemoReservePage();
       softAssert.assertEquals(blazedemoReservePage.flightNumber.getText(),expectedFlightNumber);
       softAssert.assertEquals(blazedemoReservePage.airlineName.getText(),expectedAirlineName);
       softAssert.assertEquals(blazedemoReservePage.price.getText(),expectedPrice);

       softAssert.assertAll();

    }
}
