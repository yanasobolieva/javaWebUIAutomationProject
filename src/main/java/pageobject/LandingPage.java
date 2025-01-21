package pageobject;

import base.BasePage;
import driver.WebDriverHolder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends BasePage {
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

    public LandingPage(){
        super();
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
        return new ProductCatalogPage();
    }

    public String getErrorMessage(){
        waitForElement(errorToast, true);
        return errorToast.getText();
    }

    public ForgotPasswordPage goToForgotPassword(){
        forgotPassword.click();
        return new ForgotPasswordPage();
    }

    public RegisterPage goToRegister(Boolean byButton){
        if(byButton) {
            registerButton.click();
        } else {
            registerLink.click();
        }
        return new RegisterPage();
    }
}
