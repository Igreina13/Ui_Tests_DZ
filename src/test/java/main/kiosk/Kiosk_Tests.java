package main.kiosk;

import factory.WebDriverFactory;
import logs.Assert_Logs;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.MainPage;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.assertAll;

public class Kiosk_Tests {

    //подключаем логгер
    private final Logger log = LogManager.getLogger(Kiosk_Tests.class);
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
        String mode = "--kiosk";
        driver = webDriverFactory.create(mode);

        mainPage = new MainPage(driver);
        logs = new Assert_Logs();
        mainPage.open();
    }

    /**
     * Задание №2
     * Открыть Chrome в режиме киоска
     * Перейти на ресурс
     * Нажать на "Открыть модальное окно"
     * Проверить что открылось в модальное окно
     */

    @Test
    @DisplayName("Домашняя работа №2: Модальное окно")
    void ifModalShowAndHideIsCorrect() {

        //видимость до открытия
        boolean invisibleBeforeOpening = mainPage.isInvisibleMyModal();
        mainPage.clickOpenModal();

        //видимость после открытия
        boolean visibleAfterOpening = mainPage.isVisibleMyModal();
        mainPage.clickCloseModal();

        //видимость после закрытия
        boolean invisibleAfterClosing = mainPage.isInvisibleMyModal();

        String browser = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
        assertAll(
                () -> logs.logs(invisibleBeforeOpening,
                        "ifModalShowAndHideIsCorrect > invisibleBeforeOpening",
                        log,
                        browser),

                () -> logs.logs(visibleAfterOpening,
                        "ifModalShowAndHideIsCorrect > visibleAfterOpening",
                        log,
                        browser),

                () -> logs.logs(invisibleAfterClosing,
                        "ifModalShowAndHideIsCorrect > invisibleAfterClosing",
                        log,
                        browser)
        );

    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

}
