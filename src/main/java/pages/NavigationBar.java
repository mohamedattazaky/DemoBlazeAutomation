package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitils.ActionBot;
import validation.Verification;
import logs.LogsManager;

public class NavigationBar {

    private final WebDriver driver;
    private final ActionBot actionBot;
    private final Verification verification;

    public NavigationBar(WebDriver driver){
        this.driver = driver;
        this.actionBot = new ActionBot(driver);
        this.verification = new Verification(driver);
    }

    private final By homeButton = By.xpath("//a[contains(text(),'Home')]");
    private final By contactButton = By.xpath("//a[contains(text(),'Contact')]");
    private final By aboutButton = By.xpath("//a[contains(text(),'About')]");
    private final By cartButton = By.xpath("//a[contains(text(),'Cart')]");
    private final By loginButton = By.xpath("//a[contains(text(),'Log')]");
    private final By signupButton = By.xpath("//a[contains(text(),'Sign')]");

    private final By verifyContact = By.xpath("//label[contains(text(),'Contact')]");
    private final By verifyAbout = By.xpath("//h5[contains(text(),'About')]");
    private final By verifyLogin = By.xpath("//h5[contains(text(),'Log')]");
    private final By verifySignup = By.xpath("//h5[contains(text(),'Sign')]");

    public void clickOnHomeButton(){
        LogsManager.info("Clicking Home button");
        actionBot.click(homeButton);
        LogsManager.info("Home button clicked");
    }

    public void clickOnContactButton(){
        LogsManager.info("Clicking Contact button");
        actionBot.click(contactButton);
        LogsManager.info("Contact button clicked");
    }

    public void clickOnCartButton(){
        LogsManager.info("Clicking Cart button");
        actionBot.click(cartButton);
        LogsManager.info("Cart button clicked");
    }

    public void clickOnAboutButton(){
        LogsManager.info("Clicking About button");
        actionBot.click(aboutButton);
        LogsManager.info("About button clicked");
    }

    public void clickOnLoginButton(){
        LogsManager.info("Clicking Login button");
        actionBot.click(loginButton);
        LogsManager.info("Login button clicked");
    }

    public void clickOnSignupButton(){
        LogsManager.info("Clicking Signup button");
        actionBot.click(signupButton);
        LogsManager.info("Signup button clicked");
    }

    public void verifyHomePage(){
        LogsManager.info("Verifying Home page URL");
        verification.assertUrl("https://demoblaze.com/index.html");
        LogsManager.info("Home page URL verified");
    }

    public void verifyCartPage(){
        LogsManager.info("Verifying Cart page URL");
        verification.assertUrl("https://demoblaze.com/cart.html");
        LogsManager.info("Cart page URL verified");
    }

    public void verifyContact(){
        LogsManager.info("Verifying Contact page elements");
        verification.elementIsDisplayed(verifyContact);
        LogsManager.info("Contact page elements verified");
    }

    public void verifyAbout(){
        LogsManager.info("Verifying About page elements");
        verification.elementIsDisplayed(verifyAbout);
        LogsManager.info("About page elements verified");
    }

    public void verifyLogin(){
        LogsManager.info("Verifying Login page elements");
        verification.elementIsDisplayed(verifyLogin);
        LogsManager.info("Login page elements verified");
    }

    public void verifySignup(){
        LogsManager.info("Verifying Signup page elements");
        verification.elementIsDisplayed(verifySignup);
        LogsManager.info("Signup page elements verified");
    }
}
