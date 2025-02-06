package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbsPages{

        public MainPage(WebDriver driver) {
            super(driver, "/training.html");
        }

        //обозначаем локаторы нужных нам объектов
        private By textInputId = By.id("textInput");
        private By closeModalId = By.id("closeModal");
        private By myModalId = By.id("myModal");
        private By openModalBtnId = By.id("openModalBtn");
        private By inputNameId = By.id("name");
        private By inputEmailId = By.id("email");
        private By messageBoxId = By.id("messageBox");
        private By thisFormId = By.id("sampleForm");
        private By submitBtnCss = By.cssSelector("#sampleForm button");
        private By hideBtnId = By.id("toggleMessage");

//Создаем методы
    //Метод ввода текста
    public void writeSomeTextIntoInput (String someText) {
        driver.findElement(textInputId).sendKeys(someText);
    }

    //Метод получения значения поля textInputId
    public String getTextFromInput () {
        return driver.findElement(textInputId).getAttribute("value");
    }

    //Метод проверки видимости модального окна
    public boolean isVisibleMyModal() {
        return waiters.waitForElementVisible(driver.findElement(myModalId));
    }

    //Метод проверки невидимости модального окна
    public boolean isInvisibleMyModal() {
        return waiters.waitForElementNotVisible(driver.findElement(myModalId));
    }

    //Метод открывающий модальное окно
    public void clickOpenModal() {
        driver.findElement(openModalBtnId).click();
    }

    //Метод закрывающий модальное окно
    public void clickCloseModal() {
        driver.findElement(closeModalId).click();
    }

    //Метод для ввода someName в поле inputNameId
    public void writeIntoInputName(String someName) {
        driver.findElement(inputNameId).sendKeys(someName);
    }

    //Метод для ввода someEmail в поле inputEmailId
    public void writeIntoInputEmail(String someEmail) {
        driver.findElement(inputEmailId).sendKeys(someEmail);
    }
    //Метод отправляющий форму
    public void submitForm(){
        driver.findElement(thisFormId).submit();
    }

    //Метод отправляющий форму с кликом на кнопку "Отправить"
    public void clickForSubmitForm(){
        driver.findElement(submitBtnCss).click();
    }

    //Метод получающий текст в messageBoxId
    public String getTextValueOfMessageBox(){
        return driver.findElement(messageBoxId).getText();
    }

    //Метод проверяющий наличии значений someName и someEmail в messageBoxId
    public boolean ifMessageBoxMatchesValuesWhichCameFromForm(String someName, String someEmail) {
        return getTextValueOfMessageBox().matches("(.*)" + someName + "(.*)" + someEmail + "(.*)");
    }

    //Метод для клика кнопки hideBtnId
    public void clickHideMessageBox() {
        driver.findElement(hideBtnId).click();
    }

    //Метод очищающий поле inputNameId
    public void clearInputName() {
        driver.findElement(inputNameId).clear();
    }

    //Метод очищающий поле inputEmailId
    public void clearInputEmail() {
        driver.findElement(inputEmailId).clear();
    }

    }
