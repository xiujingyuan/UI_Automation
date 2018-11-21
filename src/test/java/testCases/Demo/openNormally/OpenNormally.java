package testCases.Demo.openNormally;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import web.common.BaseAction;
import web.common.Configurator;
import web.common.ElementOperation;
import web.pageObjects.Capital.login.LoginPage;


public class OpenNormally extends BaseAction {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @BeforeTest
    public void beforeTest() {
        try {
            //获取访问url
            driver.get(Configurator.getURL());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        ElementOperation.waitForElementPresent(driver, LoginPage.capital_unloginMark);
    }

    @Test
//    用户名、密码 参数化
    @Parameters({"username","password"})
    public void testOpenNormally(String username, String password) throws Exception {
//        没有封装
        driver.findElement(By.id("loginform-username")).click();
        driver.findElement(By.id("loginform-username")).sendKeys(username);
        driver.findElement(By.id("loginform-password")).click();
        driver.findElement(By.id("loginform-password")).sendKeys(password);
        driver.findElement(By.id("login-form")).submit();
        System.out.println(driver.findElement(By.className("content")).getText());

        ElementOperation.waitForElementPresent(driver,By.className("content"));
        ElementOperation.alertOperation(driver,true);
    }

    @AfterTest(groups = { "functest"})
    public void afterTest() {
        wait3s();
        driver.quit();
    }

    @Override
    public void test() {

    }
}
