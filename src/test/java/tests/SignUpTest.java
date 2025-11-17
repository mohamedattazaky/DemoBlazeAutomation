package tests;

import dataReader.JsonReader;
import drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Contact;
import pages.NavigationBar;
import pages.SignUp;
import timeManager.TimeManage;

public class SignUpTest {
    WebDriver driver;
    NavigationBar navigationBar;
    SignUp signUp ;
    JsonReader signUpData;

    @BeforeClass
    public void preconditions() {
        signUpData = new JsonReader("signup-data");
    }
    @BeforeMethod
    public void setup() {
        driver = WebDriverFactory.initiateDriver("chrome");
        driver.navigate().to("https://demoblaze.com");
    }
    @Test
    public void validSignUp(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnSignupButton();
        signUp = new SignUp(driver);
        signUp.validSignUp(signUpData.getJsonData("valid_username") + TimeManage.getTimeStamp(), signUpData.getJsonData("valid_password") );
    }
    @Test
    public void signUpWithUserAlreadyExist(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnSignupButton();
        signUp = new SignUp(driver);
        signUp.userAlreadyExist(signUpData.getJsonData("username_already_exist"), signUpData.getJsonData("password_already_exist") );
    }
    @Test
    public void signUpWithEmptyFields(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnSignupButton();
        signUp = new SignUp(driver);
        signUp.signUpWithEmptyField("","");
    }
    @Test
    public void signUpWithEmptyUsernameField(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnSignupButton();
        signUp = new SignUp(driver);
        signUp.signUpWithEmptyField("",signUpData.getJsonData("valid_password"));
    }
    @Test
    public void signUpWithEmptyPasswordField(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnSignupButton();
        signUp = new SignUp(driver);
        signUp.signUpWithEmptyField(signUpData.getJsonData("valid_username"),"");
    }
    @Test
    public void signUpWithShortPassword(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnSignupButton();
        signUp = new SignUp(driver);
        signUp.invalidSignUp(signUpData.getJsonData("valid_username")+TimeManage.getTimeStamp(),"12" );
    }
    @Test
    public void signUpWithSpacesOnlyUsername(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnSignupButton();
        signUp = new SignUp(driver);
        signUp.invalidSignUp("   ","12345678");
    }
    @Test
   public void signUpWithSpecialCharactersInUsername(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnSignupButton();
        signUp = new SignUp(driver);
        signUp.invalidSignUp("@@##$$","12345678");
    }
    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeDriver();
    }
}
