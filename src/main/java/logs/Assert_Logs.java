package logs;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Assert_Logs {
    private WebDriver driver = null;
    private Logger logger = null;

    //дефолтный конструктор
    public Assert_Logs(){};
    public void logs(boolean condition, String message, Logger logger, String currentBrowser) {

        message = String.format("%-125s",
                String.format("%-11s", "[" + currentBrowser + "]")
                        + "-> "
                        + message
        );

        String messagePass = message + " > PASS";
        String messageFail = message + " > FAIL";

        if (condition) logger.info(messagePass);
        else logger.warn(messageFail);

        assertTrue(condition);
    }

}
