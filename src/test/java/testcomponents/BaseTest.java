package testcomponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pageobject.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        String browserName = System.getProperty("browser") != null
                ? System.getProperty("browser")
                : getPropertiesFromFile().getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if(browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public Properties getPropertiesFromFile() throws IOException {
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")
                +"//src//main//java//resources//GlobalData.properties");
        properties.load(file);
        return properties;
    }

    public void goTo() throws IOException {
        driver.get(getPropertiesFromFile().getProperty("baseURL"));
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //Read JSON to String
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")
                + filePath), StandardCharsets.UTF_8);
        //Convert JSON to HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper
                .readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        //return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
        return testCaseName + ".png";
    }

    @BeforeMethod (alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        goTo();
        return landingPage;
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(){
        driver.close();
    }
}
