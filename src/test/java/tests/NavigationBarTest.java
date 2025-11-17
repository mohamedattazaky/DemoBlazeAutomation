package tests;

import drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.NavigationBar;

public class NavigationBarTest {
    WebDriver driver;
    NavigationBar navigationBar;

    @BeforeMethod
    public void setup() {
        driver = WebDriverFactory.initiateDriver("chrome");
        driver.navigate().to("https://demoblaze.com");
    }
    @Test
    public void verifyHomePage(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnHomeButton();
        navigationBar.verifyHomePage();
    }
    @Test
    public void verifyContactPage(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnContactButton();
        navigationBar.verifyContact();
    }
    @Test
    public void verifyAboutPage(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnAboutButton();
        navigationBar.verifyAbout();
    }
    @Test
    public void verifyCartPage(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnCartButton();
        navigationBar.verifyCartPage();
    }
    @Test
    public void verifyLoginPage(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        navigationBar.verifyLogin();
    }
    @Test
    public void verifySignupPage(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnSignupButton();
        navigationBar.verifySignup();
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeDriver();
    }

}
