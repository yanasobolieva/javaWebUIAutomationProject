package base;

import driver.WebDriverHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.Header;
import pageobject.LandingPage;

import java.time.Duration;

public class BasePage {
    private Header header;

    @FindBy(xpath = "//button[text()=' Sign Out ']")
    private WebElement logoutButton;

    public BasePage() {
        PageFactory.initElements(WebDriverHolder.getInstance().getDriver(), this);
        header = new Header();
    }

    public Header getHeader() {
        return header;
    }

    public void waitForElement(By findBy, boolean appear) {
        WebDriverWait wait = new WebDriverWait(WebDriverHolder.getInstance().getDriver(), Duration.ofSeconds(10));
        if(appear){
            wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
        } else {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
        }
    }

    public void waitForElement(WebElement element, boolean appear) {
        WebDriverWait wait = new WebDriverWait(WebDriverHolder.getInstance().getDriver(), Duration.ofSeconds(10));
        if(appear){
            wait.until(ExpectedConditions.visibilityOf(element));
        } else {
            wait.until(ExpectedConditions.invisibilityOf(element));
        }
    }

    public LandingPage logout(){
        logoutButton.click();
        return new LandingPage();
    }
}
