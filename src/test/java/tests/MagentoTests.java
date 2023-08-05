package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.MagentoMainPage;
import pages.MagentoSignInPage;
import pages.MagentoSignupPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

public class MagentoTests extends TestBase {

    // DDT - Data-Driven-Testing -> Testing with multiple sets of data
    @DataProvider(name = "createAccountData")
    public static Object[][] testData(){
       return  new Object[] []{                       //multidimensional array of Object
               {"Shivanth","Kasireddy","qQ123456!"},  // The  test will run once for every array in the DataProvider
//               {"Mo","King","qQ123456!"},
//               {"Mary-Beth","Clark","qQ123456!"}
       };


    }
    String userEmail;
    String userPassword;
    String firstName;
    String lastName;
    @Test(groups = {"regression", "smoke", "magento", "login"},dataProvider = "createAccountData")
    public void verifyCreateAccount(String firstName,String lastName,String userPwd) {
        driver.get(ConfigReader.getProperty("MagentoURL"));

        MagentoMainPage magentoMainPage = new MagentoMainPage();
        magentoMainPage.createAccountLink.click();

        MagentoSignupPage magentoSignupPage = new MagentoSignupPage();
        magentoSignupPage.firstnameInput.sendKeys(firstName);
        magentoSignupPage.lastnameInput.sendKeys(lastName);


//        Random randNum = new Random();
//        int num = randNum.nextInt(10);
//        String randEmail = "Guli"+num+"@gmail.com";

//        UUID uuid = UUID.randomUUID();
//     userEmail = "user" + uuid + "@gmail.com";
      userEmail = BrowserUtils.getRandomEmail();
     this.userPassword = userPwd;
     this.firstName = firstName;
     this.lastName = lastName;

        System.out.println(userEmail);

        magentoSignupPage.emailInput.sendKeys(userEmail);
        magentoSignupPage.passwordInput.sendKeys(userPwd);
        magentoSignupPage.confirmPassword.sendKeys(userPwd);

        magentoSignupPage.createAccountBtn.click();

        Assert.assertEquals(driver.getTitle(), "My Account");
    }
    @Test(groups = {"regression", "smoke", "magento", "login"},dependsOnMethods = {"verifyCreateAccount"})
    public void verifySignIn() throws IOException {
        driver.get(ConfigReader.getProperty("MagentoURL"));
        MagentoMainPage magentoMainPage = new MagentoMainPage();
        magentoMainPage.signInLink.click();

        MagentoSignInPage magentoSignInPage = new MagentoSignInPage();
        magentoSignInPage.emailInput.sendKeys(userEmail);
        magentoSignInPage.passwordInput.sendKeys(userPassword);
        magentoSignInPage.signInBtn.click();

        String expectedMessage = "Welcome, "+firstName+" "+lastName+"!";

//        WebDriverWait wait = new WebDriverWait(driver,30);
//        wait.until(ExpectedConditions.textToBePresentInElement(magentoMainPage.welcomeMessage,expectedMessage));
        BrowserUtils.waitForTextToBePresentInElement(magentoMainPage.welcomeMessage,expectedMessage);
        String actualMessage = magentoMainPage.welcomeMessage.getText();

        Assert.assertEquals(actualMessage,expectedMessage);

    }
}
