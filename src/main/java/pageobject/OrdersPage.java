package pageobject;

import base.BasePage;
import driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends BasePage {
    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;

    public OrdersPage(){
        super();
    }

    public Boolean verifyProductInOrderDisplay(String productName){
        Boolean match = productNames.stream().anyMatch(product ->
                product.getText().equalsIgnoreCase(productName));
        return match;
    }
}
