package testCases.Blog;

import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import web.actionObjects.Blog.LoginSuccessful;
import web.common.BaseAction;
import web.common.Configurator;
import web.common.ElementOperation;
import web.pageObjects.Blog.LoginPage;

/**
 * Created by Merry on 11/1/16.
 */
public class LoginTestCase extends BaseAction {
    private static final Logger logger = LoggerFactory.getLogger(LoginTestCase.class);
    @BeforeTest(groups = { "functest"})
        public void beforeTest() {
        try {
            driver.get(Configurator.getURL());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        ElementOperation.waitForElementPresent(driver, LoginPage.loginMark) ;
    }

    @Test(groups = { "functest"})
    @Parameters({"username", "password"})
    public void test(String username, String password) throws InterruptedException {
        LoginSuccessful loginAction = new LoginSuccessful();
        loginAction.login(driver,username,password);
        ElementOperation.waitForElementPresent(driver, LoginPage.welcomeAreaLink);//验证登录成功
        setCheckPoint(driver,LoginPage.welcomeAreaLink,0,"欢迎你，晚风那个吹you");  //检查界面字段值
        check();

    }

        @AfterTest(groups = { "functest"})
        public void afterTest() {
            wait5s();
//            driver.close();
            driver.quit();
    }

    public void test() {

    }
}