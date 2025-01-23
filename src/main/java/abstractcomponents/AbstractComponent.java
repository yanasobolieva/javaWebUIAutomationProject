package abstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.Header;

import java.time.Duration;

public class AbstractComponent {

    private WebDriver driver;
    private Header header;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        header = new Header(driver);
    }

    public Header getHeader(){
        return header;
    }

    public void waitForElement(By findBy, boolean appear) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if(appear){
            wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
        } else {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
        }
    }

    public void waitForElement(WebElement element, boolean appear) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        if(appear){
            wait.until(ExpectedConditions.visibilityOf(element));
        } else {
            wait.until(ExpectedConditions.invisibilityOf(element));
        }
    }
}
