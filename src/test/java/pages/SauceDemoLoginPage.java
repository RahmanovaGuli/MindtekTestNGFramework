package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class SauceDemoLoginPage {

    WebDriver driver;

    public SauceDemoLoginPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    public WebElement userNameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginBtn;

    public void login() {
        driver.get(ConfigReader.getProperty("SauceDemoURL"));
        userNameInput.sendKeys(ConfigReader.getProperty("SaucedemoUsernamePositive"));
        passwordInput.sendKeys(ConfigReader.getProperty("Saucedemopassword"));
        loginBtn.click();
    }
}
