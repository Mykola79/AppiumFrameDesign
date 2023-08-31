package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.AppiumUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidBaseTest extends AppiumUtils {

    public AppiumDriverLocalService service;
    public AndroidDriver driver;




    @BeforeMethod(alwaysRun = true)
    public void configureAppium() throws MalformedURLException {


        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Somemulator");//emulator/virtual device
        //options.setDeviceName("Android Device");//real device/physical device
        options.setChromedriverExecutable("C:\\Users\\Admin\\IdeaProjects\\AppiumProject\\Drivers\\chromedriver.exe");

        //options.setApp("C:\\Users\\Admin\\IdeaProjects\\AppiumProject\\AppiumFiles\\APKFiles\\resources\\ApiDemos-debug.apk");

        options.setApp("C:\\Users\\Admin\\IdeaProjects\\AppiumProject\\AppiumFiles\\APKFiles\\resources\\General-Store.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void longPressAction(WebElement ele){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),"duration",2000));
    }

    public void scrollToEndAction() {

        // just scroll down till end
        boolean canScrollMore;

        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));
        } while (canScrollMore);
    }

    public void swipeAction(WebElement ele, String direction){

        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
                "direction", direction,
                "percent", 0.75
        ));
    }

    public void dragAndDropAction(WebElement ele){

        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "endX", 815,
                "endY", 733
        ));
    }

    public Double getFormattedAmount(String amount){
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }



    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        //driver.quit();
        //service.stop();

    }
}


