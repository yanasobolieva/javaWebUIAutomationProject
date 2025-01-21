package pageobject;

import base.BasePage;
import driver.WebDriverHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {
    @FindBy(css = "[placeholder='Select Country']")
    private WebElement countryField;

    @FindBy(css = ".ta-item:nth-of-type(1)")
    private WebElement selectCoutry;

    @FindBy(css = ".action__submit")
    private WebElement submitButton;

    By results = By.cssSelector(".ta-results");

    public CheckoutPage(){
        super();
    }

    public void selectCoutry(String countryName){
        Actions act = new Actions(WebDriverHolder.getInstance().getDriver());
        act.sendKeys(countryField, countryName).build().perform();
        waitForElement(results,true);
        selectCoutry.click();
    }

    public ConfirmationPage submitOrder(){
        submitButton.click();
        return new ConfirmationPage();
    }
}
