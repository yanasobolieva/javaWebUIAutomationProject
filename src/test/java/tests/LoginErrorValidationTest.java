package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import testcomponents.BaseTest;
import testcomponents.Retry;


public class LoginErrorValidationTest extends BaseTest {
    @Test (groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void incorrectCred(){
        String email = "yibiv@chosenx.com";
        String password = "cg6G@bficYJi6U4";

        landingPage.loginApplication(email, password);
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }

}
