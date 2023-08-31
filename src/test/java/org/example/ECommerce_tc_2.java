package org.example;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ECommerce_tc_2 extends AndroidBaseTest{



    //@BeforeMethod
  // public void preSetup(){
   // driver.findElement(AppiumBy.accessibilityId("Preference")).click();
     //   driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
   // }

    @Test
    public void FillForm_ErrorValidation(){

        //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).click();
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //based on Toast the attribute can be retrieved by name
        String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals("Please enter your name",toastMessage);
}


    @Test
    public void fillForm_PositiveFlow() {
        //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("My Name");
    driver.hideKeyboard();
    driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
    driver.findElement(By.id("android:id/text1")).click();
    driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
    driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
    driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();


    //based on Toast the attribute can be retrieved by name
    //Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);


}




}
