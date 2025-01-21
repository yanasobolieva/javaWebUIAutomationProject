package pageobject;

import base.BasePage;
import driver.WebDriverHolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends BasePage {
    @FindBy(css = ".mb-3")
    private List<WebElement> products;

    private By productsBy = By.cssSelector(".mb-3");
    private By addToCartButton = By.cssSelector("button:last-of-type");
    private By toastMessage = By.id("toast-container");
    private By spinner = By.className("ng-animating");

    public ProductCatalogPage(){
        super();
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
