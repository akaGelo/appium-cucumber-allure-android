package steps;

import io.cucumber.java.ru.И;
import org.openqa.selenium.By;

import static util.Wrapper.clickElement;

public class FoundPointsSteps extends BaseSteps {

    @И("в найденных городах выбираем {string}")
    public void вНайденныхГородахВыбираем(String city) {
        clickElement(driver.findElement(By.xpath("//android.widget.TextView[@text = '" + city + "']")));
        makeScreenshot(0.5);
    }

}
