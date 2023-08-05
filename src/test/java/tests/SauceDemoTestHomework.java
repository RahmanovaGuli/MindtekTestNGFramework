package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceDemoLoginPage;
import pages.SauceDemoMainPage;
import utilities.ConfigReader;
import utilities.TestBase;

import java.util.List;

public class SauceDemoTestHomework extends TestBase {

    /*
    Test Case 1: Validating login functionality with invalid password
1. Navigate to https://www.saucedemo.com/
2. Sign in with username standard_user and password invalid_password
3. Validate error message 'Epic sadface: Username and password do not match any user in this service'

Test Case 2: Validating ADD TO CART functionality
1. Navigate to https://www.saucedemo.com/
2. Sign in with username  standard_user and password secret_sauce
3. Click to 'ADD TO CART' on any product
4. Validate that product is added to Cart

Test Case 3: Validating remove button on cart
1. Navigate to https://www.saucedemo.com/
2. Sign in with username  standard_user and password   secret_sauce
3. Click to 'ADD TO CART' on any product
4. Then go to Cart and remove the product from Cart
5. Validate that product is removed from Cart

     */
    @Test
    public void validateErrorMessage(){

        driver.get(ConfigReader.getProperty("SauceDemoURL"));
        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();
        sauceDemoLoginPage.userNameInput.sendKeys(ConfigReader.getProperty("SaucedemoUsernamePositive")); // login
        sauceDemoLoginPage.passwordInput.sendKeys(ConfigReader.getProperty("Saucedemoinvalidpassword")); // password
        sauceDemoLoginPage.loginBtn.click();
        String actualMsg = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3\n")).getText();
        String expectedMSg = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(actualMsg,expectedMSg,"Wrong error message");

    }
    @Test
    public void addToCardFun(){
        driver.get(ConfigReader.getProperty("SauceDemoURL"));
        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();
        sauceDemoLoginPage.userNameInput.sendKeys(ConfigReader.getProperty("SaucedemoUsernamePositive")); // login
        sauceDemoLoginPage.passwordInput.sendKeys(ConfigReader.getProperty("Saucedemopassword")); // password
        sauceDemoLoginPage.loginBtn.click();

        SauceDemoMainPage sauceDemoMainPage = new SauceDemoMainPage();
        sauceDemoMainPage.addToCartBtn.click();
        List<WebElement> itemsBeforeRemove = driver.findElements(By.xpath("//*[@class='inventory_item_name']"));
        List<WebElement> itemsAfterRemove = driver.findElements(By.className("cart_item"));
        System.out.println(itemsAfterRemove.size());
        Assert.assertTrue(itemsBeforeRemove.size()>0);
    }
    @Test
    public void validateRemovedFromCartWithList(){
            driver.get(ConfigReader.getProperty("SauceDemoURL"));
        SauceDemoLoginPage sauceDemoLoginPage = new SauceDemoLoginPage();
            sauceDemoLoginPage.userNameInput.sendKeys(ConfigReader.getProperty("SaucedemoUsernamePositive")); // login
            sauceDemoLoginPage.passwordInput.sendKeys(ConfigReader.getProperty("Saucedemopassword")); // password
            sauceDemoLoginPage.loginBtn.click();

        SauceDemoMainPage sauceDemoMainPage = new SauceDemoMainPage();
        sauceDemoMainPage.addToCartBtn.click();
            sauceDemoMainPage.cartIcon.click();

    List<WebElement> itemsBeforeRemove = driver.findElements(By.xpath("//*[@class='inventory_item_name']"));
            driver.findElement(By.id("remove-sauce-labs-backpack")).click(); // remove btn
            System.out.println(itemsBeforeRemove.size());
    List<WebElement> itemsAfterRemove = driver.findElements(By.className("cart_item"));
            System.out.println(itemsAfterRemove.size());
            Assert.assertTrue(itemsBeforeRemove.size()>0);
            Assert.assertEquals(itemsAfterRemove.size(), 0);
}

}
