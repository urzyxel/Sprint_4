package ru.praktikum_services.qa_scooter.order;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class PageOrder {
    private WebDriver driver;

    // форма для кого Самокат
    private By editName = By.xpath("(//div[@class= 'Order_Form__17u6u']//input)[1]"); // поле Имя
    private By editSurName = By.xpath("(//div[@class= 'Order_Form__17u6u']//input)[2]"); // поле Фамилия
    private By editAddress = By.xpath("(//div[@class= 'Order_Form__17u6u']//input)[3]"); // поле Адрес
    private By editMetroStation = By.xpath("(//div[@class= 'Order_Form__17u6u']//input)[4]"); // поле Станция метро
    private By editPhoneNumber = By.xpath("(//div[@class= 'Order_Form__17u6u']//input)[5]"); //поле Номер телефона
    private By buttonNext = By.cssSelector("div.Order_NextButton__1_rCA button"); // кнопка Далее

    // форма Про Аренду
    private By buttonDate = By.xpath("//div[@class= 'react-datepicker__input-container']/input"); // поле Когда привезти самокат
    private By buttonPeriodRent = By.xpath("//div[contains(@class, 'Dropdown-control')]//div[contains(text(), '* Срок аренды')]"); // поле Срок аренды
    private By checkBoxBlack = By.xpath("//input[@id='black' and @class='Checkbox_Input__14A2w']"); // выбор цвета - чёрный
    private By checkBoxGrey = By.xpath("//input[@id='grey' and @class='Checkbox_Input__14A2w']"); // выбор цвета - серый
    private By editСourierСomment = By.cssSelector("div.Input_InputContainer__3NykH input[placeholder='Комментарий для курьера']"); // поле Комментприй для курьера
    private By buttonOrder = By.cssSelector("div.Order_Buttons__1xGrp button:not(.Button_Inverted__3IF-i)"); // кнопка Заказать

    // модальное окно подверждения заказа
    private By buttonYes = By.xpath("//div[@class='Order_Modal__YZ-d3']//button[@class='Button_Button__ra12g Button_Middle__1CSJM']"); // кнопка Да
    private By buttonNo = By.xpath("//div[@class='Order_Modal__YZ-d3']//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']"); // кнопка Нет

    //модальное окно об успешном создании заказа
    private By labelSuccessOrder = By.cssSelector("div.Order_ModalHeader__3FDaJ");

    public PageOrder(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFormsUser(String name, String surName, String address, String metroStation, String phoneNumber) {
        driver.findElement(editName).click();
        driver.findElement(editName).clear();
        driver.findElement(editName).sendKeys(name);

        driver.findElement(editSurName).click();
        driver.findElement(editSurName).clear();
        driver.findElement(editSurName).sendKeys(surName);

        driver.findElement(editAddress).click();
        driver.findElement(editAddress).clear();
        driver.findElement(editAddress).sendKeys(address);

        driver.findElement(editMetroStation).click();
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/descendant::li//div[contains(text(), '" + metroStation + "')]")));;
        element.click();

        driver.findElement(editPhoneNumber).click();
        driver.findElement(editPhoneNumber).clear();
        driver.findElement(editPhoneNumber).sendKeys(phoneNumber);
    }

    public void fillRent(String date, int periodRent, String colourScooter, String courierСomment){
        driver.findElement(buttonDate).sendKeys(date);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();

        driver.findElement(buttonPeriodRent).click();
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Dropdown-menu']//div[" + periodRent +"]")));;
        element.click();

        if (Objects.equals(colourScooter, "black")){
            driver.findElement(checkBoxBlack).click();
        } else {
            driver.findElement(checkBoxGrey).click();
        }

        driver.findElement(editСourierСomment).click();
        driver.findElement(editСourierСomment).clear();
        driver.findElement(editСourierСomment).sendKeys(courierСomment);
    }

    public void clickButtonNext(){
        driver.findElement(buttonNext).click();
    }

    public void clickButtonOrder(){
        driver.findElement(buttonOrder).click();
    }

    public void clickButtonYes(){
        driver.findElement(buttonYes).click();
    }

    public String getTextLabelSuccessOrder(){
        return driver.findElement(labelSuccessOrder).getText();
    }

}
