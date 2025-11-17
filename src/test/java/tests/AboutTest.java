package tests;

import drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.About;
import pages.NavigationBar;

public class AboutTest {
    WebDriver driver;
    About about;
    NavigationBar navigationBar;

    @BeforeMethod
    public void setup() {
        driver = WebDriverFactory.initiateDriver("chrome");
        driver.navigate().to("https://demoblaze.com");
    }
    @Test
    public void verifyPlayVideo(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnAboutButton();
        about = new About(driver);
        about.clickOnPlayVideoButton();
        about.clickOnCloseButton();
    }
    @Test
    public void verifyPauseVideo(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnAboutButton();
        about = new About(driver);
        about.clickOnPlayVideoButton();
        about.clickOnPauseVideoButton();
        about.clickOnCloseButton();
    }
    @Test
    public void verifyMuteVideo(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnAboutButton();
        about = new About(driver);
        about.clickOnPlayVideoButton();
        about.clickOnMuteButton();
        about.clickOnCloseButton();
    }
    @Test
    public void verifyFullScreenVideo(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnAboutButton();
        about = new About(driver);
        about.clickOnPlayVideoButton();
        about.clickOnFullScreenButton();
    }
    @Test
    public void verifyPictureInPictureVideo(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnAboutButton();
        about = new About(driver);
        about.clickOnPlayVideoButton();
        about.clickOnPictureInPictureButton();
        about.clickOnCloseButton();
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeDriver();
    }
}
