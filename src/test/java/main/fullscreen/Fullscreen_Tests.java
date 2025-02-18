package main.fullscreen;

import factory.WebDriverFactory;
import logs.Assert_Logs;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.MainPage;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static org.junit.jupiter.api.Assertions.assertAll;

public class Fullscreen_Tests {

    //подключаем логгер
    private final Logger log = LogManager.getLogger(Fullscreen_Tests.class);
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
        String mode = "maximize";
        driver = webDriverFactory.create(mode);

        mainPage = new MainPage(driver);
        logs = new Assert_Logs();
        mainPage.open();
    }

    /**
     * Задание №3
     * Открыть Chrome в режиме полного экрана
     * Перейти на ресурс
     * В форму ввести Имя и Почту, нажать отправить
     * В поле динамическое сообщение (на зеленом фоне) появится сообщение в формате:
     * Форма отправлена с именем: фыв и email: asdf@sdfg.rt
     */

    @Test
    @DisplayName("Домашняя работа №3: Заполнение и отправление формы")
    void ifTextGotValuesFromForm() {

        String someName = "Nastya";
        String someEmail = "Nastya@test.com";

        mainPage.writeIntoInputName(someName);
        mainPage.writeIntoInputEmail(someEmail);
        mainPage.clickForSubmitForm();
        boolean ifDivContainsValuesWhenDisplayed =
                mainPage.ifMessageBoxMatchesValuesWhichCameFromForm(someName, someEmail);

        String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();

        assertAll(
                () -> logs.logs(ifDivContainsValuesWhenDisplayed,
                        "ifTextGotValuesFromForm > ifDivContainsValuesWhenDisplayed: "
                                + someName + " " + someEmail,
                        log,
                        browser));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }
}
