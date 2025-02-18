package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import waiters.Waiters;

public abstract class AbsPages {

    private String baseUrl = System.getProperty("base.url");
    protected WebDriver driver;
    protected Waiters waiters;
    private String path;

    public AbsPages(WebDriver driver, String path) {
        this.driver = driver;
        this.path = path;
        waiters = new Waiters(driver);
    }

    public void open() {
        driver.get(baseUrl + path);
    }

    //Вынесла общий метод ввода текста
    public void inputText(String value, By input) {
        driver.findElement(input).sendKeys(value);
    }
}
