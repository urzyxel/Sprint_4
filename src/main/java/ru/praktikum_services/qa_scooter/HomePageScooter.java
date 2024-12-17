package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageScooter {
    private WebDriver driver;

    private By orderButtonHeader = By.xpath("//div[@class = 'Header_Nav__AGCXC']/button[@class = 'Button_Button__ra12g']"); // кнопка Заказать в верхней части страницы
    private By orderButtonFinish = By.xpath("//div[@class = 'Home_FinishButton__1_cWm']/button"); // кнопка Заказать в нижней части части страницы
    private By textFormOrder = By.cssSelector("div.Order_Header__BZXOb"); // текст первой формы заказа
    private String dropDownListText; // текст выпадающего списка


    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }

    public String getTextFormOrder(){
        return driver.findElement(textFormOrder).getText();
    }

    public void clickOrderButtonFinish() {
        WebElement element = driver.findElement(orderButtonFinish);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public By getTitleList(int listNumber) {
        return By.id("accordion__heading-" + listNumber);
    }

    public String getDropDownListText(int listNumber) {
        dropDownListText = "accordion__panel-" + listNumber;
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(dropDownListText)));
        return driver.findElement(By.id(dropDownListText)).getText();
    }
}
