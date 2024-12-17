import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;


public abstract class SeleniumTestBase {
    protected WebDriver driver;

    public WebDriver getWeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return webDriver;
    }

    @Before
    public void setUp() {
        driver = getWeDriver();
        Assert.assertTrue("Ошибка инициализации WebDriver", driver != null);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
