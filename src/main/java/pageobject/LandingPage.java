package pageobject;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    private WebDriver driver;

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "userPassword")
    private WebElement passwordField;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(css = "[class*=flyInOut]")
    private WebElement errorToast;

    @FindBy(css = ".forgot-password-link")
    private WebElement forgotPassword;

    @FindBy(css = ".text-reset")
    private WebElement registerLink;

    @FindBy(css = "a[routerlink='/auth/register']")
    private WebElement registerButton;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LandingPage formIsLoaded(){
        waitForElement(emailField, true);
        waitForElement(passwordField, true);
        waitForElement(loginButton, true);
        waitForElement(forgotPassword, true);
        waitForElement(registerLink, true);
        waitForElement(registerButton, true);
        return this;
    }

    public ProductCatalogPage loginApplication(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
        return new ProductCatalogPage(driver);
    }

    public String getErrorMessage(){
        waitForElement(errorToast, true);
        return errorToast.getText();
    }

    public ForgotPasswordPage goToForgotPassword(){
        forgotPassword.click();
        return new ForgotPasswordPage(driver);
    }

    public RegisterPage goToRegister(Boolean byButton){
        if(byButton) {
            registerButton.click();
        } else {
            registerLink.click();
        }
        return new RegisterPage(driver);
    }
}
