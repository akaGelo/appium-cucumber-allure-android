package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class SelectedPointsPage {

    @AndroidFindBy(id = "countryName")
    public AndroidElement countryName;

    @AndroidFindBy(id = "namePoint")
    public AndroidElement cityName;

    @AndroidFindBy(id = "backToStart")
    public AndroidElement backToStart;

    public SelectedPointsPage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


}
