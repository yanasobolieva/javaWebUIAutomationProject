package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {

    private WebDriver driver;

    @FindBy(css = "[routerlink*='cart']")
    private WebElement cartButton;

    @FindBy(css = "[routerlink*='myorders']")
    private WebElement ordersButton;

    public Header(WebDriver driver){
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
