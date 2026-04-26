package driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class WebDriverHolder {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverHolder() {
    }

    public static void initDriver() {
        if (driver.get() == null) {
            WebDriver webDriver = WebDriverFactory.initDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.set(webDriver);
        }
    }

    public static WebDriver getDriver() {
        WebDriver webDriver = driver.get();

        if (webDriver == null) {
            throw new IllegalStateException("WebDriver is not initialized for current thread. Call WebDriverHolder.initDriver() first.");
        }

        return webDriver;
    }

    public static void killDriver() {
        WebDriver webDriver = driver.get();

        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }

    public static JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) getDriver();
    }
}