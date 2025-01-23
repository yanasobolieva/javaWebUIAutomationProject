package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobject.*;
import testcomponents.BaseTest;

import java.io.IOException;

public class StepDefinitionImplementation extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogPage productCatalog;
    public Header header;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public ConfirmationPage confirmationPage;


    @Given("User landed on Landing page")
    public void user_landed_on_Landing_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^User is logged in with username (.+) and password (.+)$")
    public void user_is_logged_in_with_username_and_password(String username, String password){
        productCatalog = landingPage.loginApplication(username, password);
    }

    @When("^User adds product (.+) to the Cart$")
    public void user_adds_product_to_cart(String productName){
        productCatalog.addProductToCart(productName);
    }

    @When("^User checkout (.+)$")
    public void user_checkout_product(String productName){
        header = new Header(driver);
        cartPage = header.goToCartPage();
        Boolean match = cartPage.verifyProductDisplayed(productName);
        Assert.assertTrue(match);
        checkoutPage = cartPage.goToCheckout();
    }

    @When("^User selects (.+) for delivery$")
    public void user_selects_country(String country){
        checkoutPage.selectCoutry(country);
    }

    @When("User submits the order")
    public void user_submits_order(){
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on Confirmation Page")
    public void message_is_displayed_on_confirmation_page(String string){
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed on landing page")
    public void error_message_is_displayed_on_landing(String string) {
       String errorMessage = landingPage.getErrorMessage();
        Assert.assertTrue(errorMessage.equalsIgnoreCase(string));
        driver.close();
    }
}
