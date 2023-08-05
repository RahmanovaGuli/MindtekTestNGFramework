package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class BlazedemoReservePage {

    WebDriver driver;

    public BlazedemoReservePage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//p[contains(text(),'Airline')]")
    public WebElement airlineName;

    @FindBy(xpath = "//p[contains(text(),'Flight Number')]")
    public WebElement flightNumber;

    @FindBy(xpath = "//p[contains(text(),'Price')]")
    public WebElement price;

}
