package testcomponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import driver.WebDriverHolder;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import pageobject.LandingPage;
import utils.PropertiesReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

public class BaseTest {
    public LandingPage landingPage;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws IOException, InterruptedException {
        goToUrl();
        landingPage = new LandingPage();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite(){
        WebDriverHolder.getInstance().killDriver();
    }

    public void goToUrl(String url){
        WebDriverHolder.getInstance().getDriver().get(url);
    }

    public void goToUrl() throws IOException {
        goToUrl(PropertiesReader.getInstance().getProperty("baseURL"));
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
}
