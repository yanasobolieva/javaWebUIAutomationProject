package pageobject;

import base.BasePage;
import driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {
    @FindBy(css = ".totalRow button")
    private WebElement checkoutButton;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> productsInCart;

    public CartPage(){
        super();
    }

    public Boolean verifyProductDisplayed(String productName) {
        Boolean match = productsInCart.stream().anyMatch(cartProduct ->
                cartProduct.getText().equals(productName));
        return match;
    }

    public CheckoutPage goToCheckout(){
         checkoutButton.click();
        return new CheckoutPage();
        }
}