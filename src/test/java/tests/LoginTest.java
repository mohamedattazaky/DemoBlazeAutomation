package tests;

import dataReader.JsonReader;
import drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Login;
import pages.NavigationBar;
import pages.SignUp;

public class LoginTest {
    WebDriver driver;
    NavigationBar navigationBar;
    Login login;
    JsonReader loginData;

    @BeforeClass
    public void preconditions() {
        loginData = new JsonReader("login-data");
    }
    @BeforeMethod
    public void setup() {
        driver = WebDriverFactory.initiateDriver("chrome");
        driver.navigate().to("https://demoblaze.com");
    }
    @Test
    public void validLogin(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password") );
    }
    @Test
    public void verifyLogout(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password"));
        login.verifyLogout();
    }
    @Test
    public void invalidLoginWithWrongPassword(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.invalidLogin_WrongPassword(loginData.getJsonData("valid_username"), loginData.getJsonData("wrong_password") );
    }
    @Test
    public void invalidLoginWithWrongUsername(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.invalidLogin_UsernameNotExist(loginData.getJsonData("wrong_username"), loginData.getJsonData("valid_password") );
    }
    @Test
    public void invalidLoginWithEmptyFields(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.invalidLogin_EmptyField("","");
    }
    @Test
    public void invalidLoginWithEmptyUsernameField(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.invalidLogin_EmptyField(loginData.getJsonData("valid_username"), "");
    }
    @Test
    public void invalidLoginWithEmptyPasswordField(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.invalidLogin_EmptyField("", loginData.getJsonData("valid_password"));
    }
    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeDriver();
    }
}
