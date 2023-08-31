package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AppiumUtils;

import java.io.IOException;

public class Listeners extends AppiumUtils implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReporterObject();
    AndroidDriver driver;

    @Override
    public void onTestStart(ITestResult result){
        test = extent.createTest(result.getMethod().getMethodName());
    }
    @Override
    public void onTestSuccess(ITestResult result){
        test.log(Status.PASS,"Test Passed");
    }



    @Override
    public void onTestFailure(ITestResult result){
        test.fail(result.getThrowable());

        try {
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());


        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }


        try {
            test.addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(),driver),result
                    .getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void onFinish(ITestContext context){
        extent.flush();
    }



}
