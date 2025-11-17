package tests;

import dataReader.JsonReader;
import drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cart;
import pages.Home;
import pages.Login;
import pages.NavigationBar;

public class CartTest {
    WebDriver driver;
    NavigationBar navigationBar;
    Home home;
    Cart cart;
    Login login;
    JsonReader loginData;
    JsonReader categoryData;
    JsonReader orderData;

    @BeforeClass
    public void preconditions() {
        loginData = new JsonReader("login-data");
        categoryData = new JsonReader("category-data");
        orderData = new JsonReader("order-data");

    }
    @BeforeMethod
    public void setup() {
        driver = WebDriverFactory.initiateDriver("chrome");
        driver.navigate().to("https://demoblaze.com");
    }
    @Test
    public void verifyAddToCart(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password") );
        home = new Home(driver);
        home.choosePhone(categoryData.getJsonData("phone"));
    }
    @Test
    public void verifyProductDetails(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password") );
        home = new Home(driver);
        home.choosePhone(categoryData.getJsonData("phone"));
        cart = new Cart(driver);
        cart.verifyProductDetails();
    }
    @Test
    public void verifyDeleteProduct(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password") );
        home = new Home(driver);
        home.chooseLaptop(categoryData.getJsonData("laptop"));
        navigationBar.clickOnCartButton();
        cart = new Cart(driver);
        cart.verifyDeleteProduct();
    }
    @Test
    public void verifyValidPlaceOrder(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password") );
        home = new Home(driver);
        home.choosePhone(categoryData.getJsonData("phone"));
        cart = new Cart(driver);
        cart.verifyProductDetails();
        cart.clickOnPlaceOrder();
        cart.placeOrder(
                orderData.getJsonData("valid_name"),
                orderData.getJsonData("valid_country"),
                orderData.getJsonData("valid_city"),
                orderData.getJsonData("valid_creditCard"),
                orderData.getJsonData("valid_month"),
                orderData.getJsonData("valid_year"));
    }
    @Test
    public void verifyEmptyName_PlaceOrder(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password") );
        home = new Home(driver);
        home.choosePhone(categoryData.getJsonData("phone"));
        cart = new Cart(driver);
        cart.verifyProductDetails();
        cart.clickOnPlaceOrder();
        cart.emptyPlaceOrder(
                "",
                orderData.getJsonData("valid_country"),
                orderData.getJsonData("valid_city"),
                orderData.getJsonData("valid_creditCard"),
                orderData.getJsonData("valid_month"),
                orderData.getJsonData("valid_year"));
    }
    @Test
    public void verifyEmptyCreditCard_PlaceOrder(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password") );
        home = new Home(driver);
        home.choosePhone(categoryData.getJsonData("phone"));
        cart = new Cart(driver);
        cart.verifyProductDetails();
        cart.clickOnPlaceOrder();
        cart.emptyPlaceOrder(
                orderData.getJsonData("valid_name"),
                orderData.getJsonData("valid_country"),
                orderData.getJsonData("valid_city"),
                "",
                orderData.getJsonData("valid_month"),
                orderData.getJsonData("valid_year"));
    }
    @Test
    public void verifyEmptyFields_PlaceOrder(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password") );
        home = new Home(driver);
        home.choosePhone(categoryData.getJsonData("phone"));
        cart = new Cart(driver);
        cart.verifyProductDetails();
        cart.clickOnPlaceOrder();
        cart.emptyPlaceOrder("","","","","","");
    }
    @Test
    public void verifyInvalidMonth_PlaceOrder(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password") );
        home = new Home(driver);
        home.choosePhone(categoryData.getJsonData("phone"));
        cart = new Cart(driver);
        cart.verifyProductDetails();
        cart.clickOnPlaceOrder();
        cart.verifyInvalidMonth(
                orderData.getJsonData("valid_name"),
                orderData.getJsonData("valid_country"),
                orderData.getJsonData("valid_city"),
                orderData.getJsonData("valid_creditCard"),
                "13",
                orderData.getJsonData("valid_year"));
    }
    @Test
    public void verifyInvalidYear_PlaceOrder(){
        navigationBar = new NavigationBar(driver);
        navigationBar.clickOnLoginButton();
        login = new Login(driver);
        login.validLogin(loginData.getJsonData("valid_username"), loginData.getJsonData("valid_password") );
        home = new Home(driver);
        home.choosePhone(categoryData.getJsonData("phone"));
        cart = new Cart(driver);
        cart.verifyProductDetails();
        cart.clickOnPlaceOrder();
        cart.verifyInvalidMonth(
                orderData.getJsonData("valid_name"),
                orderData.getJsonData("valid_country"),
                orderData.getJsonData("valid_city"),
                orderData.getJsonData("valid_creditCard"),
                orderData.getJsonData("valid_month"),
                "abc");
    }
    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeDriver();
    }
}
