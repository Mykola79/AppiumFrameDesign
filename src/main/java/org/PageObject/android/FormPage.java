package org.PageObject.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.AndroidActions;
import utils.AppiumUtils;

public class FormPage extends AndroidActions {

    AndroidDriver driver;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    public void setNameField(String name){
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }
    public void setActivity(){
        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore");
        driver.startActivity(activity);
    }



    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;
    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
    private WebElement maleOption;
    public void setGender(String gender) {
        if (gender.contains("Female"))
            femaleOption.click();
        else maleOption.click();
    }


    @AndroidFindBy(id="android:id/text1")
    private WebElement countrySelection;
    public void setCountrySelection(String countryName){

        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();

    }


    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;
    public ProductCatalogPage submitForm() {
        shopButton.click();
        return new ProductCatalogPage(driver);


    }



public FormPage(AndroidDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
}
}
