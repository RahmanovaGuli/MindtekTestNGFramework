package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Driver;

public class ElarLogisticsTests {

    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        driver= Driver.getDriver();
    }

        @Test
        public void testAddCompanyNameField(){
        driver.get("http://3.137.169.132/");
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("student@mindtekbootcamp.com");
        driver.findElement(By.id("id_input_pass")).sendKeys("ilovejava");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//a[@href='#/panel/companies/list']")).click();
        driver.findElement(By.xpath("//a[@href='#/panel/companies/add']")).click();

        String companyName="NorthCarolinaSouthCarolinaCaliforniaIllinoisNewYor";
        driver.findElement(By.id("id_company_name")).sendKeys(companyName);
        Assert.assertEquals(driver.findElement(By.id("id_company_name")).getAttribute("value"),companyName);
    }
        @Test
        public void testAddCompanyNameField2() {
        driver.get("http://3.137.169.132/");
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("student@mindtekbootcamp.com");
        driver.findElement(By.id("id_input_pass")).sendKeys("ilovejava");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//a[@href='#/panel/companies/list']")).click();
        driver.findElement(By.xpath("//a[@href='#/panel/companies/add']")).click();

        String companyName = "NorthCarolinaSouthCarolinaCaliforniaIllinoisNewYork";

        driver.findElement(By.id("id_company_name")).sendKeys(companyName);
        Assert.assertTrue(driver.findElement(By.id("id_company_name")).getAttribute("value").length()==50);
    }

        @Test
        public void testAddCompanyNameField3() throws InterruptedException {
        driver.get("http://3.137.169.132/");
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("student@mindtekbootcamp.com");
        driver.findElement(By.id("id_input_pass")).sendKeys("ilovejava");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//a[@href='#/panel/companies/list']")).click();
        driver.findElement(By.xpath("//a[@href='#/panel/companies/add']")).click();

        String companyName = "a";

        driver.findElement(By.id("id_company_name")).sendKeys(companyName);
        driver.findElement(By.id("id_company_name")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(2000);
        driver.findElement(By.id("id_company_name")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("id_company_name")).clear();
        Thread.sleep(2000);
       Assert.assertEquals(driver.findElement(By.xpath("//span[@class='input-errors']")).getText(),"This field is required");
    }

        @Test
        public void invalidName() throws InterruptedException {
        driver.get("http://3.137.169.132/");
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("student@mindtekbootcamp.com");
        driver.findElement(By.id("id_input_pass")).sendKeys("ilovejava");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.findElement(By.xpath("//a[@href='#/panel/companies/list']")).click();
        driver.findElement(By.xpath("//a[@href='#/panel/companies/add']")).click();

        String companyName = "$";
        Thread.sleep(2000);

        driver.findElement(By.id("id_company_name")).sendKeys(companyName);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='input-errors']")).getText(),"Invalid input");
    }
        @AfterMethod
        public void tearDown(){
            driver.quit();
        }
    }
