package pageobject;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

    private WebDriver driver;

    private String confirmationMessage = "Thankyou for the order.";

    @FindBy(css = ".hero-primary")
    private WebElement actualMessage;


    public ConfirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        return actualMessage.getText();
    }

    public Boolean successMessageDisplay(){
        return getConfirmationMessage().equalsIgnoreCase(confirmationMessage);
    }
}