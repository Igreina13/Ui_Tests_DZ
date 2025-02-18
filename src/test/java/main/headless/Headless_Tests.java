package main.headless;

import factory.WebDriverFactory;
import logs.Assert_Logs;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.MainPage;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Headless_Tests {

    //подключаем логгер
    private static final Logger logger = LogManager.getLogger(Headless_Tests.class);
    private static WebDriverFactory webDriverFactory = new WebDriverFactory();

    private WebDriver driver;
    private MainPage mainPage;
    private Assert_Logs logs;

    @BeforeAll
    public static void init() {
        webDriverFactory.webDriverManagerSetup();
    }

    @BeforeEach
    public void createDriver() {
        String mode = "--headless";
        driver = webDriverFactory.create(mode);

        mainPage = new MainPage(driver);
        logs = new Assert_Logs();
        mainPage.open();
    }

    /**
     * Задание №1
     * Открыть Chrome в headless режиме
     * Перейти на ресурс
     * В поле ввода текста ввести ОТУС
     * Проверить что в Текст соответствует введенному
     */

    @Test
    @DisplayName("Домашняя работа №1: Ввод текста в поле")
    void ifEqualsInputText() {
        //создаю строку someText и присваиваю ей значение
        String someText = "ОТУС";
        System.out.println(someText);

        mainPage.writeSomeTextIntoInput(someText);
        String textValue = mainPage.getTextFromInput();
        String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
        logs.logs(someText.equals(textValue), "ifEqualsInputText: " + someText, logger, browser);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}
