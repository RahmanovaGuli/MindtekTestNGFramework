package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.html.HTMLInputElement;
import utilities.Driver;

import java.util.List;

public class SauceDemoMainPage {

    WebDriver driver;

public SauceDemoMainPage(){
    driver = Driver.getDriver();
    PageFactory.initElements(driver,this);
}
@FindBy(xpath = "//select[@class='product_sort_container']")
    public WebElement sortDropDown;

@FindBy(xpath = "//*[@class='inventory_item_price']")
    public List<WebElement> itemPrices;

@FindBy(xpath = "//*[@id=\"add-to-cart-sauce-labs-backpack\"]")
    public WebElement addToCartBtn;

@FindBy(xpath = "//*[@id=\"shopping_cart_container\"]/a/span")
    public WebElement cartIcon;
}

