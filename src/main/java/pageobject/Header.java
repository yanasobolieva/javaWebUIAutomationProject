package pageobject;

import base.BasePage;
import driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {
    @FindBy(css = "[routerlink*='cart']")
    private WebElement cartButton;

    @FindBy(css = "[routerlink*='myorders']")
    private WebElement ordersButton;

    public Header(){
        PageFactory.initElements(WebDriverHolder.getInstance().getDriver(), this);
    }

    public CartPage goToCartPage(){
        cartButton.click();
        return new CartPage();
    }

    public OrdersPage goToOrdersPage(){
        ordersButton.click();
        return new OrdersPage();
    }
}
