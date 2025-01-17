package testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count = 0;
    int maxTry = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(count<maxTry) {
            count++;
            return true;
        }
        return false;
    }
}
