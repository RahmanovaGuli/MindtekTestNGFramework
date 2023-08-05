package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceDemoLoginPage;
import pages.SauceDemoMainPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.io.IOException;
import java.util.List;


public class SauceDemoTests extends TestBase {



    @Test(groups = {"regression","smoke","saucedemo","login"})
    public void sauceDemoLoginPositive(){
     driver.get(ConfigReader.getProperty("SauceDemoURL"));
    WebElement usernameInput = driver.findElement(By.id("user-name"));
    WebElement password = driver.findElement(By.id("password"));
    WebElement loginBtn = driver.findElement(By.id("login-button"));

    usernameInput.sendKeys(ConfigReader.getProperty("SaucedemoUsernamePositive"));
      password.sendKeys(ConfigReader.getProperty("Saucedemopassword"));
      loginBtn.click();
      WebElement shopCartBtn = driver.findElement(By.id("shopping_cart_container"));
        Assert.assertTrue(shopCartBtn.isDisplayed());
    }
   // @Ignore - ignores the test method below
    @Test(groups = {"regression","saucedemo","login"})
    public void sauceDemoLoginNegative(){
        driver.get(ConfigReader.getProperty("SauceDemoURL"));
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput=driver.findElement(By.id("password"));
        WebElement LoginBtn=driver.findElement(By.id("login-button"));
        usernameInput.sendKeys(ConfigReader.getProperty("SaucedemoLokedUser"));
        passwordInput.sendKeys(ConfigReader.getProperty("Saucedemopassword"));
        LoginBtn.click();
        String actualMsg = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        String expectedMSg = "Epic sadface: Sorry, this user has been locked out.";
     // debugging -- finding and fixing bugs or failure
    // good practice to add messages to assertion lines especially when asserting true or false
        Assert.assertEquals(actualMsg,expectedMSg,"Wrong error message");
    }

  // driver.get(ConfigReader.getProperty
    @Test(groups = {"regression","smoke","saucedemo"})
    public void sauceDemoVerifyPriceLowHigh() throws IOException {
        driver.get(ConfigReader.getProperty("SauceDemoURL"));
        BrowserUtils.takeScreenshot("TestScreenshot");
    SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();
    sauceDemoLoginPage.login();
    SauceDemoMainPage sauceDemoMainPage = new SauceDemoMainPage();
     Select sortSelect = new Select(sauceDemoMainPage.sortDropDown);
     sortSelect.selectByValue("lohi");


    for (int i=0;i<sauceDemoMainPage.itemPrices.size()-1;i++){
        double item1 = Double.parseDouble(sauceDemoMainPage.itemPrices.get(i).getText().substring(1));
        double item2 = Double.parseDouble(sauceDemoMainPage.itemPrices.get(i+1).getText().substring(1));
        Assert.assertTrue(item1<=item2);
        System.out.println(item1+" is equal to or less than "+item2);

    }

}
}
