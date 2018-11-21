package web.common;

import org.openqa.selenium.WebDriver;

/**
 * Created by Merry on 9/2/16.
 */
public abstract class BaseOps {
    public WebDriver driver = WebDrivers.getWebDriver();

    public static void waitFor(Long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void wait1s() {
        waitFor(1000L);
    }

    public static void  wait3s() {
        waitFor(3000L);
    }

    public static void wait5s() {
        waitFor(5000L);
    }

    public static void wait10s() {
        waitFor(10000L);
    }

    public static void wait20s() {
        waitFor(20000L);
    }

    public static void wait200s() {
        waitFor(200000L);
    }

}
