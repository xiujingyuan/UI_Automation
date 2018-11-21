package testCases.Capital.login;

import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import web.actionObjects.Capital.login.CapitalLoginSuccess;
import web.common.BaseAction;
import web.common.Configurator;
import web.common.ElementOperation;
import web.pageObjects.Capital.login.LoginPage;

/**
 * Created by IntelliJ IDEA.
 * User: yuanxiujing
 * Date: 18/8/15
 * Time: 上午11:38
 * 登录
 */
public class CapitalLoginTest extends BaseAction {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void beforeTest() {
        //更改窗口大小
        driver.manage().window().setSize(new Dimension(1500,1080));

        try {
            //获取访问url
            driver.get(Configurator.getURL());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        ElementOperation.waitForElementPresent(driver, LoginPage.capital_unloginMark);
    }

    /**
     * @param username
     * @param password
     * @throws Exception
     */
    @Test(description = "登录", groups = {"login_in"})
//    用户名、密码 参数化
    @Parameters({"username", "password"})
    public void login(String username, String password) throws Exception {
        CapitalLoginSuccess loginAction = new CapitalLoginSuccess();
        loginAction.login(username, password);

        wait3s();

        ElementOperation.waitForElementPresent(driver, LoginPage.capital_loginMark);//验证1.右上角出现登录名，验证登录成功
        setCheckPoint(driver, LoginPage.capital_loginMark, 0, "袁修静");  //检查界面字段值
        check();
        //验证主页面文案
        driver.findElement(LoginPage.capital_menuText).getText().contains("禁止对外提供截图");//判断是否包含文案：禁止对外提供截图

        Reporter.log("主页面文案内容：" + '\n' + driver.findElement(LoginPage.capital_menuText).getText() + '\n');
        Reporter.log("登录成功。");

        logger.info("主页面文案内容：" + '\n' + driver.findElement(LoginPage.capital_menuText).getText() + '\n');
        logger.info("登录成功。");

//        ElementOperation.waitForElementPresent(driver,LoginPage.capital_loginsuccess);//线上验证3.页面中出现"登录成功"，验证登录成功
    }

    @AfterTest(groups = {"functest"})
    public void afterTest() {
        wait1s();
        Reporter.log('\n' + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + '\n' + "###### 测试执行完成 ######");
        logger.info('\n' + "方法路径：" + Thread.currentThread().getStackTrace()[1].getClassName() + '\n' + "###### 测试执行完成 ######");
        driver.quit();
    }

    public void test() {

    }
}
