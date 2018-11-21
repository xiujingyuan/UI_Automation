package web.common;

import org.openqa.selenium.WebDriver;

/**
 * Created by Merry on 8/26/16.
 */
public abstract class BasePage extends BaseOps {
    public WebDriver driver = WebDrivers.getWebDriver();
    public abstract void prepare();

}
