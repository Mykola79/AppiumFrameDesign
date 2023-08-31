package testUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.AppiumUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class AndroidBaseTest1 extends AppiumUtils {

    public AppiumDriverLocalService service;
    public AndroidDriver driver;




    @BeforeMethod
    public void configureAppium() throws IOException {

        Properties properties = new Properties();

        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\resources\\data.properties");
        String ipAddress = System.getProperty("ipAddress")!=null?System.getProperty("ipAddress") : properties.getProperty("ipAddress");
        properties.load(fileInputStream);
        //String ipAddress = properties.getProperty("ipAddress");
        String port = properties.getProperty("port");

        service = startAppiumServer(ipAddress,Integer.parseInt(port));
        //service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                //.withIPAddress("127.0.0.1").usingPort(4723).build();
        //service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(properties.getProperty("AndroidDeviceName"));//emulator/virtual device
        //options.setDeviceName("Android Device");//real device/physical device
        options.setChromedriverExecutable("C:\\Users\\Admin\\IdeaProjects\\AppiumProject\\Drivers\\chromedriver.exe");

        //options.setApp("C:\\Users\\Admin\\IdeaProjects\\AppiumProject\\AppiumFiles\\APKFiles\\resources\\ApiDemos-debug.apk");

        options.setApp("C:\\Users\\Admin\\IdeaProjects\\AppiumProject\\AppiumFiles\\APKFiles\\resources\\General-Store.apk");

        driver = new AndroidDriver(service.getUrl(), options);
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



    @AfterMethod
    public void tearDown(){
        //driver.quit();
        //service.stop();

    }



}
