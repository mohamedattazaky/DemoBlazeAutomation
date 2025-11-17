package tests;

import dataReader.JsonReader;
import drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Home;

public class HomeTest {
    WebDriver driver;
    Home home;
    JsonReader categoryData;
    @BeforeClass
    public void preconditions() {
        categoryData = new JsonReader("category-data");
    }
    @BeforeMethod
    public void setup() {
        driver = WebDriverFactory.initiateDriver("chrome");
        driver.navigate().to("https://demoblaze.com");
    }
    @Test
    public void choosePhone(){
        home = new Home(driver);
        home.choosePhone(categoryData.getJsonData("phone"));
    }
    @Test
    public void chooseLaptop(){
        home = new Home(driver);
        home.chooseLaptop(categoryData.getJsonData("laptop"));
    }
    @Test
    public void chooseMonitor(){
        home = new Home(driver);
        home.chooseMonitor(categoryData.getJsonData("monitor"));
    }
    @Test
    public void verifyNextButton(){
        home = new Home(driver);
        home.clickOnNextButton();
    }
    @Test
    public void verifyPreviousButton(){
        home = new Home(driver);
        home.clickOnPreviousButton();
    }
    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeDriver();
    }
}
