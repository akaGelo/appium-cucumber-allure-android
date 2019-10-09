package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import runner.RunnerTest;

import static util.Wrapper.clickElement;

public class MainSteps extends BaseSteps{

    @Дано("вводим в поиск название города {string}")
    public void вводимВПоискНазваниеГорода(String city) {
        mainPage.inputField.sendKeys(city);
        makeScreenshot(0.5);
    }

    @И("нажимаем на кнопку поиска")
    public void нажимаемНаКнопкуПоиска() {
        clickElement(mainPage.searchButton);
        makeScreenshot(0.5);
    }

}
