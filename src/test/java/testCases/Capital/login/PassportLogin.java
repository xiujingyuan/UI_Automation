package testCases.Capital.login;

import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import web.actionObjects.Capital.login.PassportLoginSuccess;
import web.common.BaseAction;
import web.common.Configurator;
import web.common.ElementOperation;
import web.pageObjects.Capital.login.LoginPage;

import static web.pageObjects.Capital.login.LoginPage.capital_loginSuccess;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/17
 * Time: 下午3:40
 * 通行证登录
 */
public class PassportLogin extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    /**
     *
     * @throws Exception
     */
    @Test
    public void login() throws Exception {
        PassportLoginSuccess loginAction = new PassportLoginSuccess();
        loginAction.login(driver);

//        https协议登录，待解决；目前需要人工点击证书
//        新打开窗口，无cookie，需扫码登录；待解决

        ElementOperation.waitForElementPresent(driver, capital_loginSuccess);//验证:页面中出现"登录成功"，验证登录成功
        System.out.println("校验内容输出：" + '\n' + capital_loginSuccess + '\n');


    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait3s();
        driver.quit();
    }

    public void test() {

    }
}
