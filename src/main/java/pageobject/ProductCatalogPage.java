package pageobject;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    By productsBy = By.cssSelector(".mb-3");
    By addToCartButton = By.cssSelector("button:last-of-type");
    By toastMessage = By.id("toast-container");
    By spinner = By.className("ng-animating");

    public ProductCatalogPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getProductList(){
        waitForElement(productsBy, true);
        return products;
    }

    public WebElement getProductsByName(String productName){
        WebElement prod = getProductList()
                .stream()
                .filter(product->product.findElement(By.cssSelector("h5 b")).getText().equals(productName))
                .findFirst()
                .orElse(null);
        return prod;
    }

    public void addProductToCart(String productName){
        WebElement prod = getProductsByName(productName);
        prod.findElement(addToCartButton).click();
        waitForElement(toastMessage, true);
        waitForElement(spinner, false);
    }
}
