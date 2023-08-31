package org.example;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.PageObject.android.CartPage;
import org.PageObject.android.ProductCatalogPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.PageObject.android.FormPage;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;



public class ECommerce_tc_4_Hybrid extends AndroidBaseTest {


    @Test(dataProvider = "getData",groups = {"Smoke"})
    public void fillForm(HashMap<String,String>input) throws InterruptedException {



        FormPage formPage =new FormPage(driver);
        //ExtentTest test = extent.createTest("Initial Demo");
        formPage.setNameField(input.get("name"));
        //formPage.setNameField("My Name");
        formPage.setGender(input.get("gender"));
        //formPage.setGender("Female");
        formPage.setCountrySelection(input.get("country"));
        //formPage.setCountrySelection("Argentina");


        ProductCatalogPage productCatalogPage = formPage.submitForm();
        //ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);

        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.addItemToCartByIndex(0);
        CartPage cartPage = productCatalogPage.goToCartPage();

        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"), "text", "Cart"));


        double totalSum = cartPage.getProductSum();
        double displayFormattedSum = cartPage.getTotalAmountDisplayed();
        AssertJUnit.assertEquals(totalSum, displayFormattedSum);
        cartPage.acceptTermsConditions();
        cartPage.submitOrder();


    }
    //@BeforeMethod
    //public void preSetup(){
        //formPage.setActivity();

    //}


    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\org\\example\\testData\\eCommerce.json");

        return new Object[][]{

                //{"My Name", "Female", "Argentina"},
                //{"shetty","Male", "France"}
                {data.get(0)},
                {data.get(1)}

        };
    }


}




















        //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("My Name");
        //driver.hideKeyboard();

        //driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();

        //driver.findElement(By.id("android:id/text1")).click();
        //driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
        //driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();

        //driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        //driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        //or both
        //driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();

        //driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //if stale element exception >
        //Thread.sleep(2000);
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"), "text", "Cart"));

        //List<WebElement> products = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        //int number = products.size();
        //System.out.println("Products: "+number);

        //double sum = 0;
        //for (int i = 0; i < products.size(); i++) {

           // String words = products.get(i).getText();
            //Double price = Double.parseDouble(words.substring(1));//Double is wrapper class that converts primitive data into object
            //and string into double

            //sum = sum + price;

        //}
        //System.out.println("Total purchase Amount: " + sum);

        //String asertTotalAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        //wrap text into double
        //Double dispalyFormattedSum = getFormattedAmount(asertTotalAmount);

        //Assert.assertEquals(sum, displayFormattedSum);

        //WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        //longPressAction(ele);

        //driver.findElement(By.id("android:id/button1")).click();
        //driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        //driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnProceed")).click();
        //Thread.sleep(5000);

        //now we transferred from native app to hybrid

        //Set<String> contexts = driver.getContextHandles();

        //for (String contextName : contexts
        //) {
            //System.out.println(contextName);
        //}


        //driver.context("WEBVIEW_com.androidsample.generalstore");//chrome driver

        //driver.findElement(By.name("q")).sendKeys("electric zoo");
        //driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        //driver.pressKey(new KeyEvent(AndroidKey.BACK));
        //driver.context("NATIVE_APP");


