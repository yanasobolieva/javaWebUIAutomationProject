package pageobject;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = ".totalRow button")
    WebElement checkoutButton;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> productsInCart;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyProductDisplayed(String productName) {
        Boolean match = productsInCart.stream().anyMatch(cartProduct ->
                cartProduct.getText().equals(productName));
        return match;
    }

    public CheckoutPage goToCheckout(){
         checkoutButton.click();
        return new CheckoutPage(driver);
        }
}