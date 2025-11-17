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

public class ContactTest {
    WebDriver driver;
    NavigationBar navigationBar;
    Contact contact;
    JsonReader contactData;

    @BeforeClass
    public void preconditions() {
        contactData = new JsonReader("contact-data");
    }
    @BeforeMethod
    public void setup() {
        driver = WebDriverFactory.initiateDriver("chrome");
        driver.navigate().to("https://demoblaze.com");
    }
    @Test
    public void validContact(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnContactButton();
        contact = new Contact(driver);
        contact.fillValidContactForm(
                contactData.getJsonData("valid_email"),
                contactData.getJsonData("valid_name"),
                contactData.getJsonData("valid_message") );
    }
    @Test
    public void InValidContactEmpty(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnContactButton();
        contact = new Contact(driver);
        contact.fillInValidContactForm("","","");
    }
    @Test
    public void InValidContactWithInValidEmail(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnContactButton();
        contact = new Contact(driver);
        contact.fillInValidContactForm(
                contactData.getJsonData("invalid_email"),
                contactData.getJsonData("valid_name"),
                contactData.getJsonData("valid_message") );
    }
    @Test
    public void InValidContactWithInEmptyEmail(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnContactButton();
        contact = new Contact(driver);
        contact.fillInValidContactForm(
                "",
                contactData.getJsonData("valid_name"),
                contactData.getJsonData("valid_message") );
    }
    @Test
    public void InValidContactWithInEmptyName(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnContactButton();
        contact = new Contact(driver);
        contact.fillInValidContactForm(
                contactData.getJsonData("valid_email"),
                "",
                contactData.getJsonData("valid_message") );
    }
    @Test
    public void InValidContactWithInEmptyMessage(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnContactButton();
        contact = new Contact(driver);
        contact.fillInValidContactForm(
                contactData.getJsonData("valid_email"),
                contactData.getJsonData("valid_name"),
                 "");
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeDriver();
    }
}
