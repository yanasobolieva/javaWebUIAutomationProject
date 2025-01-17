package E2E;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args){
        String email = "yibiv465523@chosenx.com";
        String password = "cg6G@bficYJi6U4";
        String productName = "QWERTY";

        //WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("login")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products
                .stream()
                .filter(product->product.findElement(By.cssSelector("h5 b")).getText().equals(productName))
                .findFirst()
                .orElse(null);

        assert prod != null;
        prod.findElement(By.cssSelector("button:last-of-type")).click();
        //prod.findElement(By.xpath("//button[text()=' Add To Cart']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> productsInCart = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean match = productsInCart.stream().anyMatch(cartProduct->
                cartProduct.getText().equals(productName));

        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions act = new Actions(driver);
        act
                .sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india")
                .build()
                .perform();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));

        driver.findElement(By.cssSelector(".ta-item:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
        driver.close();
    }
}
