package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitils.ActionBot;
import uitils.Alerts;
import validation.Verification;
import logs.LogsManager;

public class SignUp {

    private final WebDriver driver;
    private final ActionBot actionBot;
    private final Verification verification;
    private final Alerts alerts;

    public SignUp(WebDriver driver){
        this.driver = driver;
        this.alerts = new Alerts(driver);
        this.actionBot = new ActionBot(driver);
        this.verification = new Verification(driver);
    }

    private final By userName = By.id("sign-username");
    private final By password = By.id("sign-password");
    private final By signUpButton = By.xpath("//button[.='Sign up']");

    public void validSignUp(String userField,String passField){
        LogsManager.info("Filling valid sign up form with Username: " + userField);

        actionBot.sendKeys(userName,userField);
        actionBot.sendKeys(password,passField);

        LogsManager.info("Clicking Sign Up button");
        actionBot.click(signUpButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert received: " + alertText);
        verification.assertEquals(alertText, "Sign up successful.","Expected success sign up alert not displayed");

        alerts.acceptalerts();
        LogsManager.info("Sign up successful, alert accepted");
    }

    public void userAlreadyExist(String userField,String passField){
        LogsManager.info("Trying to sign up with existing username: " + userField);

        actionBot.sendKeys(userName,userField);
        actionBot.sendKeys(password,passField);

        LogsManager.info("Clicking Sign Up button");
        actionBot.click(signUpButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert received: " + alertText);
        verification.assertEquals(alertText, "This user already exist.","Expected 'user already exists' alert not displayed");
    }

    public void signUpWithEmptyField(String userField,String passField){
        LogsManager.info("Trying to sign up with empty fields");

        actionBot.sendKeys(userName,userField);
        actionBot.sendKeys(password,passField);

        LogsManager.info("Clicking Sign Up button");
        actionBot.click(signUpButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert received: " + alertText);
        verification.assertEquals(alertText, "Please fill out Username and Password.","Expected 'Please fill out Username and Password' alert not displayed");
    }

    public void invalidSignUp(String userField,String passField){
        LogsManager.info("Trying invalid sign up with Username: " + userField);

        actionBot.sendKeys(userName,userField);
        actionBot.sendKeys(password,passField);

        LogsManager.info("Clicking Sign Up button");
        actionBot.click(signUpButton);

        String actualMsg = alerts.getalertstext();
        LogsManager.info("Alert received: " + actualMsg);

        verification.assertTrue(
                actualMsg.contains("Please fill out") || actualMsg.contains("fail"),
                "Expected failure alert not displayed. Actual: " + actualMsg
        );

        LogsManager.info("Invalid sign up handled successfully");
    }
}
