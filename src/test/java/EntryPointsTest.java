import org.junit.Test;
import ru.praktikum_services.qa_scooter.HomePageScooter;

import static org.junit.Assert.assertEquals;

public class EntryPointsTest extends SeleniumTestBase {
    private String expectedTextForm;
    private String actuallyText = "Для кого самокат";

    @Test
    public void checkButtonHeaderFormOrder(){
        driver.get("https://qa-scooter.praktikum-services.ru");
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        homePageScooter.clickOrderButtonHeader();
        expectedTextForm = homePageScooter.getTextFormOrder();
        assertEquals(expectedTextForm, actuallyText);
    }

    @Test
    public void checkButtonFinishFormOrder(){
        driver.get("https://qa-scooter.praktikum-services.ru");
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        homePageScooter.clickOrderButtonFinish();
        expectedTextForm = homePageScooter.getTextFormOrder();
        assertEquals(expectedTextForm, actuallyText);
    }
}
