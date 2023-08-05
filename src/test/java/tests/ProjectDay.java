package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigReader;

import java.util.concurrent.TimeUnit;

public class ProjectDay {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigReader.getProperty("Smartbear"));
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("student@mindtekbootcamp.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ilovejava", Keys.ENTER);
        driver.findElement(By.className("side-link-company")).click();
        driver.findElement(By.className("link-btm-menu")).click();

    }
    @Test
    public void brokeCompany(){

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
