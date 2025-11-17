package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uitils.ActionBot;
import uitils.Alerts;
import validation.Validation;
import validation.Verification;
import logs.LogsManager;

public class Cart {

    private final WebDriver driver;
    private final ActionBot actionBot;
    private final Verification verification;
    private final Validation validation;
    private final Alerts alerts;

    public Cart(WebDriver driver) {
        this.driver = driver;
        this.alerts = new Alerts(driver);
        this.actionBot = new ActionBot(driver);
        this.validation = new Validation(driver);
        this.verification = new Verification(driver);
    }

    private final By cartButton = By.xpath("//a[contains(text(),'Cart')]");
    private final By addToCartButton = By.xpath("//a[.='Add to cart']");
    private final By productCartTitle = By.cssSelector("#tbodyid tr td:nth-child(2)");
    private final By productCartPrice = By.cssSelector("#tbodyid tr td:nth-child(3)");
    private final By deleteProduct = By.xpath("//a[.='Delete']");

    private final By selectedProductName = By.tagName("h2");
    private final By selectedProductPrice = By.tagName("h3");

    private final By nameField = By.id("name");
    private final By countryField = By.id("country");
    private final By cityField = By.id("city");
    private final By creditCardField = By.id("card");
    private final By monthField = By.id("month");
    private final By yearField = By.id("year");
    private final By placeOrderButton = By.xpath("//button[.='Place Order']");
    private final By purchaseButton = By.xpath("//button[.='Purchase']");
    private final By confirmationPopup = By.xpath("//h2[contains(text(), 'Thank')]");

    public void verifyProductDetails() {
        LogsManager.info("Verifying product details in cart");
        actionBot.click(cartButton);

        String cartName = actionBot.getText(productCartTitle);
        String selectedName = actionBot.getText(selectedProductName);
        LogsManager.info("Cart Name: " + cartName + " | Selected Name: " + selectedName);
        validation.assertEquals(cartName, selectedName, "Product name in cart does NOT match the selected product!");

        String cartPrice = actionBot.getText(productCartPrice);
        String selectedPrice = actionBot.getText(selectedProductPrice);
        LogsManager.info("Cart Price: " + cartPrice + " | Selected Price: " + selectedPrice);
        validation.assertEquals(cartPrice, selectedPrice, "Product price in cart does NOT match the selected product!");
    }

    public void clickOnPlaceOrder() {
        LogsManager.info("Clicking on Place Order button");
        actionBot.click(placeOrderButton);
        LogsManager.info("Place Order button clicked");
    }

    public void placeOrder(String name, String country, String city, String creditCard, String month, String year) {
        LogsManager.info("Filling Place Order form");

        actionBot.sendKeys(nameField, name);
        actionBot.sendKeys(countryField, country);
        actionBot.sendKeys(cityField, city);
        actionBot.sendKeys(creditCardField, creditCard);
        actionBot.sendKeys(monthField, month);
        actionBot.sendKeys(yearField, year);

        LogsManager.info("Submitting order");
        actionBot.click(purchaseButton);

        boolean isDisplayed = driver.findElements(confirmationPopup).size() > 0;
        LogsManager.info("Confirmation popup displayed: " + isDisplayed);
        verification.assertTrue(isDisplayed, "Confirmation popup not displayed!");
    }

    public void emptyPlaceOrder(String name, String country, String city, String creditCard, String month, String year) {
        LogsManager.info("Testing empty Place Order alert validation");

        actionBot.sendKeys(nameField, name);
        actionBot.sendKeys(countryField, country);
        actionBot.sendKeys(cityField, city);
        actionBot.sendKeys(creditCardField, creditCard);
        actionBot.sendKeys(monthField, month);
        actionBot.sendKeys(yearField, year);

        actionBot.click(purchaseButton);

        String alertText = alerts.getalertstext();
        LogsManager.info("Alert text: " + alertText);
        verification.assertEquals(alertText, "Please fill out Name and Creditcard.", "Alert is not displayed");
    }

    public void clickOnAddToCart() {
        LogsManager.info("Clicking Add to Cart button");
        actionBot.click(addToCartButton);
        LogsManager.info("Add to Cart button clicked");
    }

    public void verifyDeleteProduct() {
        LogsManager.info("Deleting product from cart");
        actionBot.click(deleteProduct);
        LogsManager.info("Product deleted successfully");
    }

    public void verifyInvalidMonth(String name, String country, String city, String creditCard, String month, String year) {
        LogsManager.info("Verifying invalid month input");

        actionBot.sendKeys(nameField, name);
        actionBot.sendKeys(countryField, country);
        actionBot.sendKeys(cityField, city);
        actionBot.sendKeys(creditCardField, creditCard);
        actionBot.sendKeys(monthField, month);
        actionBot.sendKeys(yearField, year);

        actionBot.click(purchaseButton);

        boolean isDisplayed = driver.findElements(confirmationPopup).size() > 0;
        LogsManager.info("Confirmation popup displayed with invalid month: " + isDisplayed);
        verification.assertFalse(isDisplayed, "Month should be numeric and between 1 and 12");
    }

    public void verifyInvalidYear() {
        LogsManager.info("Verifying invalid year input");

        boolean isDisplayed = driver.findElements(confirmationPopup).size() > 0;
        LogsManager.info("Confirmation popup displayed with invalid year: " + isDisplayed);
        verification.assertFalse(isDisplayed, "Year should be numeric");
    }
}
