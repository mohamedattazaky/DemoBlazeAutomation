package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitils.ActionBot;
import uitils.Alerts;
import validation.Verification;
import logs.LogsManager;

public class Home {

    private final WebDriver driver;
    private final ActionBot actionBot;
    private final Alerts alerts;
    private final Verification verification;

    public Home(WebDriver driver){
        this.driver = driver;
        this.alerts = new Alerts(driver);
        this.actionBot = new ActionBot(driver);
        this.verification = new Verification(driver);
    }

    private final By phonesCategory = By.xpath("//a[.='Phones']");
    private final By laptopsCategory = By.xpath("//a[.='Laptops']");
    private final By monitorsCategory = By.xpath("//a[.='Monitors']");
    private final By nextButton = By.xpath("//button[.='Next']");
    private final By previousButton = By.xpath("//button[.='Previous']");
    private final By addToCartButton = By.xpath("//a[.='Add to cart']");

    private By phoneName(String phone){
        return By.xpath("//a[.='"+phone+"']");
    }

    private By laptopName(String laptop){
        return By.xpath("//a[.='"+laptop+"']");
    }

    private By monitorName(String monitor){
        return By.xpath("//a[.='"+monitor+"']");
    }

    public void choosePhone(String phone){
        LogsManager.info("Selecting phone category");
        actionBot.click(phonesCategory);

        LogsManager.info("Selecting phone: " + phone);
        actionBot.click(phoneName(phone));

        LogsManager.info("Adding phone to cart");
        actionBot.click(addToCartButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert received: " + alertText);
        verification.assertEquals(alertText, "Product added", "Expected product added alert not displayed");
    }

    public void chooseLaptop(String laptop){
        LogsManager.info("Selecting laptop category");
        actionBot.click(laptopsCategory);

        LogsManager.info("Selecting laptop: " + laptop);
        actionBot.click(laptopName(laptop));

        LogsManager.info("Adding laptop to cart");
        actionBot.click(addToCartButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert received: " + alertText);
        verification.assertEquals(alertText, "Product added", "Expected product added alert not displayed");
    }

    public void chooseMonitor(String monitor){
        LogsManager.info("Selecting monitor category");
        actionBot.click(monitorsCategory);

        LogsManager.info("Selecting monitor: " + monitor);
        actionBot.click(monitorName(monitor));

        LogsManager.info("Adding monitor to cart");
        actionBot.click(addToCartButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert received: " + alertText);
        verification.assertEquals(alertText, "Product added", "Expected product added alert not displayed");
    }

    public void clickOnNextButton(){
        LogsManager.info("Clicking on Next button");
        actionBot.click(nextButton);
        LogsManager.info("Next button clicked");
    }

    public void clickOnPreviousButton(){
        LogsManager.info("Clicking on Previous button");
        actionBot.click(previousButton);
        LogsManager.info("Previous button clicked");
    }

}
