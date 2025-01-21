package pageobject;

import base.BasePage;
import driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends BasePage {
    private String confirmationMessage = "Thankyou for the order.";

    @FindBy(css = ".hero-primary")
    private WebElement actualMessage;


    public ConfirmationPage(){
        super();
    }

    public String getConfirmationMessage() {
        return actualMessage.getText();
    }

    public Boolean successMessageDisplay(){
        return getConfirmationMessage().equalsIgnoreCase(confirmationMessage);
    }
}