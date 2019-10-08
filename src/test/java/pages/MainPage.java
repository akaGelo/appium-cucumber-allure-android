package pages;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage {

    @AndroidFindBy(id = "inputField")
    public AndroidElement inputField;

    @AndroidFindBy(id = "searchButton")
    public AndroidElement searchButton;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public MainPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


}
