package hooks;


import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;

import static io.cucumber.core.event.Status.PASSED;
import static runner.RunnerTest.driver;
import static runner.RunnerTest.makeScreenshot;

public class Hooks {

    @Before
    public void startScenario(Scenario scenario) {
        System.out.println("------------------------------");
        System.out.println("Запуск сценария - '" + scenario.getName() + "'");
        System.out.println("------------------------------");
    }


    @After
    public static void checkScenarioResult(Scenario scenario) {
        System.out.println("------------------------------");
        System.out.println("Сценарий '" + scenario.getName() + "' - " + scenario.getStatus());
        if (!scenario.getStatus().equals(PASSED)) {
            makeScreenshot(0.5);
        }
        driver.resetApp();
        System.out.println("------------------------------");
    }

}
