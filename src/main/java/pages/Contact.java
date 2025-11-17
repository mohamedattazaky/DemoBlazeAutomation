package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitils.ActionBot;
import uitils.Alerts;
import validation.Verification;
import logs.LogsManager;

public class Contact {

    private final WebDriver driver;
    private final ActionBot actionBot;
    private final Verification verification;
    private final Alerts alerts;

    public Contact(WebDriver driver){
        this.driver = driver;
        this.actionBot = new ActionBot(driver);
        this.verification = new Verification(driver);
        this.alerts = new Alerts(driver);
    }

    private final By contactEmail = By.id("recipient-email");
    private final By contactName = By.id("recipient-name");
    private final By contactMessage = By.id("message-text");
    private final By sendMessageButton = By.xpath("//button[.='Send message']");

    public void fillValidContactForm(String email, String name, String message){
        LogsManager.info("Filling valid contact form with Email: " + email + ", Name: " + name);

        actionBot.sendKeys(contactEmail,email);
        actionBot.sendKeys(contactName,name);
        actionBot.sendKeys(contactMessage,message);

        LogsManager.info("Clicking on 'Send message' button");
        actionBot.click(sendMessageButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert text received: " + alertText);
        verification.assertEquals(alertText,"Thanks for the message!!","Expected success message not displayed");

        alerts.acceptalerts();
        LogsManager.info("Alert accepted, form submitted successfully");
    }

    public void fillInValidContactForm(String email, String name, String message) {
        LogsManager.info("Filling invalid contact form with Email: " + email + ", Name: " + name);

        actionBot.sendKeys(contactEmail,email);
        actionBot.sendKeys(contactName,name);
        actionBot.sendKeys(contactMessage,message);

        LogsManager.info("Clicking on 'Send message' button");
        actionBot.click(sendMessageButton);

        String actualMsg = alerts.getalertstext();
        LogsManager.info("Alert text received: " + actualMsg);

        verification.assertTrue(
                actualMsg.contains("Please fill out") || actualMsg.contains("fail"),
                "Expected failure alert not displayed. Actual: " + actualMsg
        );
        LogsManager.info("Invalid contact form handled successfully");
    }
}
