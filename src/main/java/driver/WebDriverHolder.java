package driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverHolder {
    private static WebDriverHolder instance = null;
    private static WebDriver driver;

    private WebDriverHolder(){
        driver = WebDriverFactory.initDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriverHolder getInstance(){
        if(instance==null){
            instance = new WebDriverHolder();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void killDriver(){
        if(driver != null) {
            driver.quit();
        }
    }

    public JavascriptExecutor getJavascriptExecutor(){
        return (JavascriptExecutor) driver;
    }
}