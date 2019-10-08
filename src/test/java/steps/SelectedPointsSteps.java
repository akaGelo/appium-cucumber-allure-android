package steps;

import io.cucumber.java.ru.Тогда;

import static org.junit.Assert.assertEquals;

public class SelectedPointsSteps extends BaseSteps {

    @Тогда("страна у выбранной точки {string}")
    public void странаУВыбраннойТочки(String country) {
        assertEquals("Страна не соответствует ожидаемой!", country, selectedPointsPage.countryName.getText());
        makeScreenshot(0.5);
    }

    @Тогда("город у выбранной точки {string}")
    public void городУВыбраннойТочки(String city) {
        assertEquals("Город не соответствует ожидаемому!", city, selectedPointsPage.cityName.getText());
        makeScreenshot(0.5);
    }
}
