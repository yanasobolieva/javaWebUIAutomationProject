package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",
        glue="stepdefinitions",
        monochrome=true,
        plugin={"html:reports/cucumber.html"}) // @tags = "@Regression"
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
