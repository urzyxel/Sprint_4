import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.HomePageScooter;
import ru.praktikum_services.qa_scooter.order.PageOrder;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PageOrderTest extends SeleniumTestBase {
    private final String locationButton;
    private final String expectedTextForm;
    private final String name;
    private final String surName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final int periodRent;
    private final String colourScooter;
    private final String courierСomment;
    private final String expectedTextList;

    public PageOrderTest(String locationButton, String expectedTextForm, String name, String surName, String address, String metroStation, String phoneNumber, String date, int periodRent, String colourScooter, String courierСomment, String expectedTextList) {
        this.locationButton = locationButton;
        this.expectedTextForm = expectedTextForm;
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.periodRent = periodRent;
        this.colourScooter = colourScooter;
        this.courierСomment = courierСomment;
        this.expectedTextList = expectedTextList;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"HEAD", "Для кого самокат", "Иван", "Иванов", "Ленинна, 1", "Бульвар Рокоссовского", "+79000000000", "18.12.2024", 3, "black", "Домофон 147", "Заказ оформлен"},
                {"FINISH", "Для кого самокат", "Марфа", "Сидорова", "Обручева, 10", "Сокольники", "+79000000001", "21.12.2024", 2, "grey", "После обеда", "Заказ оформлен"}
        };
    }

    @Test
    public void checkCreateOrder(){
        String actuallyText;
        driver.get("https://qa-scooter.praktikum-services.ru");
        PageOrder pageOrder = new PageOrder(driver);
        HomePageScooter homePageScooter = new HomePageScooter(driver);
        if (Objects.equals(locationButton, "HEAD")) {
            homePageScooter.clickOrderButtonHeader();
        } else {
            homePageScooter.clickOrderButtonFinish();
        }
        actuallyText = homePageScooter.getTextFormOrder();
        assertEquals(expectedTextForm, actuallyText);
        pageOrder.fillFormsUser(name, surName, address, metroStation, phoneNumber);
        pageOrder.clickButtonNext();
        pageOrder.fillRent(date, periodRent, colourScooter, courierСomment);
        pageOrder.clickButtonOrder();
        pageOrder.clickButtonYes();
        actuallyText = pageOrder.getTextLabelSuccessOrder();
        assertEquals(expectedTextList, actuallyText);
    }
}
