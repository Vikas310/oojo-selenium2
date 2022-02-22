package common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

    private int retryCount = 3;
    private static final int maxCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if(retryCount < maxCount){
            retryCount++;
            return true;
        }
        return false;
    }
}
