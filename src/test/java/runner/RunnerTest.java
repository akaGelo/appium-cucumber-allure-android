package runner;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.FoundPointsPage;
import pages.MainPage;
import pages.SelectedPointsPage;
import util.Wrapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import static util.SizeReducer.resize;

@RunWith(Cucumber.class)

@CucumberOptions(
        strict = true,
        features = {"src/test/resources/features/"},
        plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"},
        glue = {"steps", "hooks"})
public class RunnerTest {

    public static AndroidDriver<AndroidElement> driver;
    public final static String UDID = System.getProperty("udid");
    public final static boolean VIDEO_RECORDING = Boolean.parseBoolean(System.getProperty("video"));
    public static Wrapper wrapper;
    public static MainPage mainPage;
    public static FoundPointsPage foundPointsPage;
    public static SelectedPointsPage selectedPointsPage;


    @BeforeClass
    public static void setUpDriver() throws MalformedURLException {

        File app = new File("src/test/resources/apk/weatherforecast.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability("deviceName", "defaultDeviceName");
        capabilities.setCapability("udid", "XTX7N18723007267");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 80000);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        initPage();
    }

    public static void initPage() {
        wrapper = new Wrapper(driver);
        mainPage = new MainPage(driver);
        foundPointsPage = new FoundPointsPage(driver);
        selectedPointsPage = new SelectedPointsPage(driver);
    }


    /**
     * Сделать скриншот
     *
     * @param percentage изменить размер изображения, 1.0 исходный размер
     */
    public static void makeScreenshot(double percentage) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = new byte[0];
        try {
            fileContent = toByteArrayAutoClosable(resize(scrFile, percentage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Allure.getLifecycle().addAttachment("Скриншот", "image/png", "png", fileContent);
    }

    private static byte[] toByteArrayAutoClosable(BufferedImage image) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", out);
            return out.toByteArray();
        }
    }

    @Attachment(value = "Видеозапись выполнения теста", type = "video/mp4")
    public static byte[] attachmentVideo(String video) {
        return Base64.getDecoder().decode(video);
    }

}
