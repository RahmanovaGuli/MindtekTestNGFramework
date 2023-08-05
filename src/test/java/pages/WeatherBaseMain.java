package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WeatherBaseMain {
    WebDriver driver;

    public WeatherBaseMain() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[@id=\"left-170\"]/ul/li[11]/a")
    public WebElement clickNAmerica;

    @FindBy(xpath = "//div[2]/ul/li[3]/a")
    public WebElement clickUSA;

    @FindBy(xpath = "(//li/a[@class='redglow'])[6]")
    public WebElement clickCalifornia;

   @FindBy(xpath = "//a[contains(text(),'San Jose')]")
    public WebElement clickSanJose;

   @FindBy(xpath = "//a[contains(text(),'°C')]")
    public WebElement clickCelsius;



}

