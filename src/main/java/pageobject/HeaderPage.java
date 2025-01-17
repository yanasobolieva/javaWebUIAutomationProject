package pageobject;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartButton;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersButton;

    public HeaderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CartPage goToCartPage(){
        cartButton.click();
        return new CartPage(driver);
    }

    public OrdersPage goToOrdersPage(){
        ordersButton.click();
        return new OrdersPage(driver);
    }
}
