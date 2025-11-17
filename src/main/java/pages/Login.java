package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import uitils.ActionBot;
import uitils.Alerts;
import validation.Verification;
import logs.LogsManager;

public class Login {

    private final WebDriver driver;
    private final ActionBot actionBot;
    private final Verification verification;
    private final Alerts alerts;

    public Login(WebDriver driver) {
        this.driver = driver;
        this.alerts = new Alerts(driver);
        this.actionBot = new ActionBot(driver);
        this.verification = new Verification(driver);

    }

    private final By userName = By.id("loginusername");
    private final By password = By.id("loginpassword");
    private final By loginButton = By.xpath("//button[.='Log in']");
    private final By verifyLoginLabel = By.id("nameofuser");
    private final By logoutButton = By.id("logout2");

    public void validLogin(String userField, String passField) {
        LogsManager.info("Performing valid login with Username: " + userField);

        actionBot.sendKeys(userName, userField);
        actionBot.sendKeys(password, passField);

        LogsManager.info("Clicking Login button");
        actionBot.click(loginButton);

        LogsManager.info("Verifying user is logged in");
        verification.elementIsDisplayed(verifyLoginLabel);
        LogsManager.info("User logged in successfully");
    }

    public void verifyLogout() {
        LogsManager.info("Clicking Logout button");
        actionBot.click(logoutButton);

        WebElement verifyLogin = driver.findElement(By.id("nameofuser"));
        verification.assertFalse(
                verifyLogin.isDisplayed(),
                "Element still visible when it shouldn't be."
        );
        LogsManager.info("User logged out successfully");
    }

    public void invalidLogin_WrongPassword(String userField, String passField) {
        LogsManager.info("Performing invalid login (wrong password) with Username: " + userField);

        actionBot.sendKeys(userName, userField);
        actionBot.sendKeys(password, passField);

        LogsManager.info("Clicking Login button");
        actionBot.click(loginButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert received: " + alertText);
        verification.assertEquals(alertText, "Wrong password.", "Expected 'Wrong password' alert not displayed");
    }

    public void invalidLogin_UsernameNotExist(String userField, String passField) {
        LogsManager.info("Performing invalid login (username not exist) with Username: " + userField);

        actionBot.sendKeys(userName, userField);
        actionBot.sendKeys(password, passField);

        LogsManager.info("Clicking Login button");
        actionBot.click(loginButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert received: " + alertText);
        verification.assertEquals(alertText, "User does not exist.", "Expected 'User does not exist' alert not displayed");
    }

    public void invalidLogin_EmptyField(String userField, String passField) {
        LogsManager.info("Performing invalid login (empty fields)");

        actionBot.sendKeys(userName, userField);
        actionBot.sendKeys(password, passField);

        LogsManager.info("Clicking Login button");
        actionBot.click(loginButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert received: " + alertText);
        verification.assertEquals(alertText, "Please fill out Username and Password.", "Expected 'Please fill out Username and Password' alert not displayed");
    }
}
